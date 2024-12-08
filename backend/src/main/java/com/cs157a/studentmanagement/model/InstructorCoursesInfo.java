package com.cs157a.studentmanagement.model;

import com.cs157a.studentmanagement.model.id_class.EnrollmentsId;
import com.cs157a.studentmanagement.model.id_class.InstructorCoursesInfoId;
import jakarta.persistence.*;
import java.sql.Time;

import java.util.List;

@Entity
@Table(name = "instructor_courses_info")
@IdClass(InstructorCoursesInfoId.class)
public class InstructorCoursesInfo {

   @EmbeddedId
   @ManyToOne
   @JoinColumns({
           @JoinColumn(name = "instructor_id"), // CONSTRAINT FOREIGN KEY
           @JoinColumn(name = "course_id")      // CONSTRAINT FOREIGN KEY
   })
   private InstructorCoursesInfoId id;

   @Column(name = "max_enrollment", nullable = false, columnDefinition = "INTEGER")
   private Integer maxEnrollment;

   @Column(name = "num_enrolled", nullable = false, columnDefinition = "INTEGER")
   private Integer numEnrolled;

   @Column(name = "start_time", nullable = false, columnDefinition = "TIME")
   private Time startTime;

   @Column(name = "end_time", nullable = false, columnDefinition = "TIME")
   private Time endTime;


}
