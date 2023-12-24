package com.casestudy.emr.emr.payload.request;

import jakarta.validation.constraints.NotBlank;

/**
 * Represents a login request.
 * 
 * The LoginRequest class stores the username and password for a login request.
 * It ensures that both fields are not empty or null by using the @NotBlank
 * annotation.
 * 
 * Example Usage:
 * 
 * LoginRequest loginRequest = new LoginRequest();
 * loginRequest.setUsername("john");
 * loginRequest.setPassword("password");
 * 
 * String username = loginRequest.getUsername();
 * String password = loginRequest.getPassword();
 */
public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    /**
     * Returns the value of the username field.
     * 
     * @return The value of the username field.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username field.
     * 
     * @param username The value to set for the username field.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the value of the password field.
     * 
     * @return The value of the password field.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password field.
     * 
     * @param password The value to set for the password field.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
