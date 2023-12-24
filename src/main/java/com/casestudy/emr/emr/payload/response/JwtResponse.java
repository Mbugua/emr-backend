package com.casestudy.emr.emr.payload.response;

import java.util.List;

/**
 * Represents a JSON Web Token (JWT) response.
 * Contains the token, token type, user ID, username, email, and roles
 * associated with the user.
 */
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;

    /**
     * Constructs a JwtResponse object with the provided access token, user ID,
     * username, email, and roles.
     * 
     * @param accessToken The access token.
     * @param id          The user ID.
     * @param username    The username.
     * @param email       The email.
     * @param roles       The roles associated with the user.
     */
    public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    /**
     * Returns the access token.
     * 
     * @return The access token.
     */
    public String getAccessToken() {
        return token;
    }

    /**
     * Sets the access token.
     * 
     * @param accessToken The access token to set.
     */
    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    /**
     * Returns the token type.
     * 
     * @return The token type.
     */
    public String getTokenType() {
        return type;
    }

    /**
     * Sets the token type.
     * 
     * @param tokenType The token type to set.
     */
    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    /**
     * Returns the user ID.
     * 
     * @return The user ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the user ID.
     * 
     * @param id The user ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the email.
     * 
     * @return The email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     * 
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the username.
     * 
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     * 
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the roles associated with the user.
     * 
     * @return The roles.
     */
    public List<String> getRoles() {
        return roles;
    }
}
