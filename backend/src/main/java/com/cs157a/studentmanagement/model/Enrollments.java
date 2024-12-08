package com.cs157a.studentmanagement.model;

import com.cs157a.studentmanagement.model.id_class.EnrollmentsId;
import com.cs157a.studentmanagement.utils.enums.Grades;
import jakarta.persistence.*;

@Entity
@Table(name = "enrollments")
@IdClass(EnrollmentsId.class)
public class Enrollments {

   @EmbeddedId
   @ManyToOne
   @JoinColumns({ // CONSTRAINTS FOREIGN KEY
           @JoinColumn(name = "student_id", referencedColumnName = "studentId"),
           @JoinColumn(name = "instructor_id", referencedColumnName = "instructorId"),
           @JoinColumn(name = "course_id", referencedColumnName = "courseId")
   })
   private EnrollmentsId enrollmentsId;

   @Enumerated(EnumType.STRING)
   @Column(name = "grade", nullable = false)
   Grades grade;


}
