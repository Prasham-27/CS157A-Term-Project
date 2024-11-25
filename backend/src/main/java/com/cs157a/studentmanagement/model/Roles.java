package com.cs157a.studentmanagement.model;

import jakarta.persistence.*;

/**
 * Defines user roles
 */
@Entity
@Table(name = "roles")
public class Roles {

   /**
    * An auto incremented SMALLINT role_id
    */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_seq")
   @SequenceGenerator(name = "role_id_seq", sequenceName = "role_id_seq", allocationSize = 1)
   @Column(name = "role_id", columnDefinition = "SMALLINT")
   private Short id;

   /**
    * The name of the role
    */
   @Column(name = "role_name", nullable = false, unique = true,
           columnDefinition = "VARCHAR(40)")
   private String name;

   /**
    * The destination of a FORIEGN KEY, the actual constraint is in users
    */
   @OneToOne(mappedBy = "role")
   Users user;

}