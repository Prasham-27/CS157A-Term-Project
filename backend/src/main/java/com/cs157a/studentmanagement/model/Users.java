package com.cs157a.studentmanagement.model;

import jakarta.persistence.*;

/**
 * Defines the user accounts table
 */
@Entity
@Table(name = "users")
public class Users {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "user_id")
   private Long id;

   @Column(name = "email", nullable = false, unique = true,
           columnDefinition = "VARCHAR(60)")
   private String email;

   @Column(name = "password", nullable = false, columnDefinition = "CHAR(60)")
   private String password;

   @Column(name = "first_name", nullable = false,
           columnDefinition = "VARCHAR(30)")
   private String firstName;

   @Column(name = "last_name", nullable = false,
           columnDefinition = "VARCHAR(30)")
   private String lastName;

   // TODO Add a check to see if the the role exists in the roles table
   @ManyToOne
   @JoinColumn(name = "role_id", nullable = false) // CONSTRAINT Foreign key
   private Roles role;

}