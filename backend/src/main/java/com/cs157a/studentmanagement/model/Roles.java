package com.cs157a.studentmanagement.model;

import jakarta.persistence.*;

import java.util.List;

/**
 * Defines user roles
 */
@Entity
@Table(name = "roles")
public class Roles {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "role_id")
   private Integer id;

   /**
    * The name of the role
    */
   @Column(name = "role_name", nullable = false, unique = true,
           columnDefinition = "VARCHAR(40)")
   private String name;

   /**
    * The destination of a FORIEGN KEY, the actual constraint is in users
    */
   @OneToMany(mappedBy = "role")
   private List<Users> users;


}