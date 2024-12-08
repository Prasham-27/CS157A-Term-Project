package com.cs157a.studentmanagement.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "departments")
public class Departments {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "dept_id")
   private Integer id;

   @Column(name = "dept_name", nullable = false, unique = true,
           columnDefinition = "VARCHAR(40)")
   private String name;

   @Column(name = "dept_abbreviation", nullable = false, unique = true,
           columnDefinition = "VARCHAR(5)")
   private String abbreviation;

   /**
    * The destination of a FORIEGN KEY, the actual constraint is in users
    */
   @OneToMany(mappedBy = "department")
   private List<Courses> courses;

}
