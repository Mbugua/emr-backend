package com.casestudy.emr.emr.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.emr.emr.models.ERole;
import com.casestudy.emr.emr.models.Role;
import com.casestudy.emr.emr.models.User;
import com.casestudy.emr.emr.payload.request.CreateUserRequest;
import com.casestudy.emr.emr.payload.request.LoginRequest;
import com.casestudy.emr.emr.payload.response.JwtResponse;
import com.casestudy.emr.emr.payload.response.MessageResponse;
import com.casestudy.emr.emr.repositories.RoleRepository;
import com.casestudy.emr.emr.repositories.UserRepository;
import com.casestudy.emr.emr.security.jwt.JwtUtils;
import com.casestudy.emr.emr.security.services.UserDetailsImpl;

/**
 * This class is an authentication controller in a Java Spring Boot application.
 * It handles user authentication by validating the user's credentials and
 * generating a JSON Web Token (JWT) for authorization purposes.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
        @Autowired
        AuthenticationManager authenticationManager;

        @Autowired
        UserRepository userRepository;

        @Autowired
        RoleRepository roleRepository;

        @Autowired
        PasswordEncoder encoder;

        @Autowired
        JwtUtils jwtUtils;

        /**
         * Authenticates a user by validating their credentials and generates a JWT
         * token for authorization.
         * Returns a ResponseEntity object containing a JwtResponse object with the
         * generated JWT and user details.
         *
         * @param loginRequest The LoginRequest object containing the user's
         *                     credentials.
         * @return A ResponseEntity object containing a JwtResponse object with the
         *         generated JWT and user details.
         */
        @PostMapping("/login")
        public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
                Authentication authentication = authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                                                loginRequest.getPassword()));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtUtils.generateJwtToken(authentication);

                UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
                List<String> roles = userDetails.getAuthorities().stream()
                                .map(item -> item.getAuthority())
                                .collect(Collectors.toList());

                return ResponseEntity.ok(new JwtResponse(jwt,
                                userDetails.getId(),
                                userDetails.getUsername(),
                                userDetails.getEmail(),
                                roles));
        }

        @PostMapping("/signup")
        public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
                if (userRepository.existsByUsername(createUserRequest.getUsername())) {
                        return ResponseEntity.badRequest()
                                        .body(new MessageResponse("Error: Username is already taken!"));
                }

                if (userRepository.existsByEmail(createUserRequest.getEmail())) {
                        return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
                }

                // create new user's account
                User user = new User(createUserRequest.getUsername(), createUserRequest.getEmail(),
                                encoder.encode(createUserRequest.getPassword()));

                Set<String> strRoles = createUserRequest.getRole();
                Set<Role> roles = new HashSet<>();

                if (strRoles == null) {
                        Role userRole = roleRepository.findByName(ERole.USER)
                                        .orElseThrow(() -> new RuntimeException("Error:Role not found"));
                        roles.add(userRole);
                } else {
                        strRoles.forEach(role -> {
                                switch (role) {
                                        case "admin":
                                                Role adminRole = roleRepository.findByName(ERole.ADMIN)
                                                                .orElseThrow(() -> new RuntimeException(
                                                                                "Role not found"));
                                                roles.add(adminRole);
                                                break;
                                        case "pharmacist":
                                                Role pharmacistRole = roleRepository.findByName(ERole.PHARMACIST)
                                                                .orElseThrow(() -> new RuntimeException(
                                                                                "Role not found"));
                                                roles.add(pharmacistRole);
                                                break;

                                        default:
                                                Role userRole = roleRepository.findByName(ERole.USER)
                                                                .orElseThrow(() -> new RuntimeException(
                                                                                "Role not found"));
                                                roles.add(userRole);
                                                break;
                                }
                        });
                }

                user.setRoles(roles);
                userRepository.save(user);

                return ResponseEntity.ok(new MessageResponse("User created successfully!"));
        }

}
