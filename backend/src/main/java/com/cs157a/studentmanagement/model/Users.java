package com.cs157a.studentmanagement.model;

import jakarta.persistence.*;

import java.util.List;

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

   @ManyToOne
   @JoinColumn(name = "role_id", nullable = false) // CONSTRAINT Foreign key
   private Roles role;

   /**
    * The destination of a FORIEGN KEY, the actual constraint is in students
    */
   @OneToOne(mappedBy = "student")
   private Students student;

   /**
    * The destination of a FORIEGN KEY, the actual constraint is in instructors
    */
   @OneToOne(mappedBy = "student")
   private Instructors instructor;



   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public Roles getRole() {
      return role;
   }

   public void setRole(Roles role) {
      this.role = role;
   }
}