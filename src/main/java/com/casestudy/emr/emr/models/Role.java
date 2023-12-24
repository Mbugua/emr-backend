package com.casestudy.emr.emr.models;

import jakarta.persistence.*;

/**
 * Represents a role in a system.
 * 
 * This class is a Java entity class that represents a role in a system. It is
 * annotated with `@Entity` to indicate that it is a persistent entity and
 * `@Table` to specify the name of the database table. The class has a primary
 * key field `id` and a field `name` of type `ERole`, which is an enumerated
 * type representing different role names. The class provides getter and setter
 * methods for the fields.
 * 
 * Example Usage:
 * ```java
 * Role role = new Role(ERole.ADMIN);
 * role.setId(1L);
 * role.getName(); // returns ERole.ADMIN
 * ```
 */
@Entity
@Table(name = "roles")
public class Role {

    /**
     * The primary key of the role.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the role.
     */
    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private ERole name;

    /**
     * Default constructor for the Role class.
     */
    public Role() {
    }

    /**
     * Constructor for the Role class that takes a name parameter.
     * 
     * @param name The name of the role.
     */
    public Role(ERole name) {
        this.name = name;
    }

    /**
     * Returns the id of the role.
     * 
     * @return The id of the role.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of the role.
     * 
     * @param id The id of the role.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the name of the role.
     * 
     * @return The name of the role.
     */
    public ERole getName() {
        return name;
    }

    /**
     * Sets the name of the role.
     * 
     * @param name The name of the role.
     */
    public void setName(ERole name) {
        this.name = name;
    }
}
