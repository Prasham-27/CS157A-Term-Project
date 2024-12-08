package com.cs157a.studentmanagement.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "majors")
public class Majors {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "major_id")
   private Integer majorId;

   @Column(name = "major_name", nullable = false, unique = true,
           columnDefinition = "VARCHAR(40)")
   private String name;

   /**
    * The destination of a FORIEGN KEY, the actual constraint is in student
    */
   @OneToMany(mappedBy = "student")
   private List<Students> students;
}
