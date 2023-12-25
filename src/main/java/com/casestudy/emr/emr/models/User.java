package com.casestudy.emr.emr.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * The User class represents a user entity in a Java application.
 * It is annotated with @Entity and @Table to indicate that it is a persistent
 * entity and should be mapped to a database table.
 * The class has fields for the user's username, email, password, and a set of
 * roles.
 * It also has getter and setter methods for these fields.
 */
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "email" }),
        @UniqueConstraint(columnNames = { "username" })
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 64)
    private String email;

    @NotBlank
    @Size(max = 64)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    /**
     * Constructor that sets the username, email, and password fields.
     * 
     * @param username The user's username.
     * @param email    The user's email.
     * @param password The user's password.
     */
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     * Returns the user's ID.
     * 
     * @return The user's ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the user's ID.
     * 
     * @param id The user's ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the user's username.
     * 
     * @return The user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username.
     * 
     * @param username The user's username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the user's email.
     * 
     * @return The user's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email.
     * 
     * @param email The user's email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the user's password.
     * 
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     * 
     * @param password The user's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the set of roles assigned to the user.
     * 
     * @return The set of roles assigned to the user.
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * Sets the set of roles assigned to the user.
     * 
     * @param roles The set of roles assigned to the user.
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
