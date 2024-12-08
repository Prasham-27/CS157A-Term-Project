package com.cs157a.studentmanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "course_info")
public class CourseInfo {

   @OneToOne
   @JoinColumn(name = "course_id", nullable = false) // CONSTRAINT Foreign key
   private Courses course;

   @Column(name = "course_name", nullable = false)
   private String courseName;

   @Column(name = "points", nullable = false)
   private double points;
}
