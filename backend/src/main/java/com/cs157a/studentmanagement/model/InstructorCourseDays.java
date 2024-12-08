package com.cs157a.studentmanagement.model;

import com.cs157a.studentmanagement.model.id_class.InstructorCoursesInfoId;
import com.cs157a.studentmanagement.utils.enums.Days;
import com.cs157a.studentmanagement.utils.enums.Grades;
import jakarta.persistence.*;

@Entity
@Table(name = "Instructor_course_days")
public class InstructorCourseDays {

   @EmbeddedId
   @ManyToOne
   @JoinColumns({
           @JoinColumn(name = "instructor_id"), // CONSTRAINT FOREIGN KEY
           @JoinColumn(name = "course_id")      // CONSTRAINT FOREIGN KEY
   })
   private InstructorCoursesInfoId id;

   @Enumerated(EnumType.STRING)
   @Column(name = "day", nullable = false)
   private Days day;
}
