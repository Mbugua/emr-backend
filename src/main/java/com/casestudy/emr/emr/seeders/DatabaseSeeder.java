
package com.casestudy.emr.emr.seeders;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.casestudy.emr.emr.repositories.RoleRepository;
import com.casestudy.emr.emr.repositories.UserRepository;
import com.casestudy.emr.emr.models.ERole;
import com.casestudy.emr.emr.models.Role;
import com.casestudy.emr.emr.models.User;

/**
 * DatabaseSeeder
 */
public class DatabaseSeeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public DatabaseSeeder(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        seedUsers();
        seedRoles();
    }

    private void seedUsers() {
        // Check if users already exist in the database
        if (userRepository.count() == 0) {
            // Create and save default users
            User user1 = new User("Jdoe", "john.doe@emr.com", "password");

            userRepository.saveAll(List.of(user1));
        }
    }

    private void seedRoles() {
        if (roleRepository.count() == 0) {
            Role adminRole = new Role(ERole.ADMIN);
            Role pharmaRole = new Role(ERole.PHARMACIST);
            Role usRole = new Role(ERole.USER);

            roleRepository.saveAll(List.of(adminRole, pharmaRole, usRole));
        }
    }
}