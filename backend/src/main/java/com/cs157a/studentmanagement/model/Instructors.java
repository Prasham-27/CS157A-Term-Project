package com.cs157a.studentmanagement.model;

import com.cs157a.studentmanagement.model.id_class.EnrollmentsId;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "instructors")
public class Instructors {

   @OneToOne // CONSTRAINT FOREIGN KEY
   @JoinColumn(name = "user_id", nullable = false, unique = true)
   Users instructorId;

   @OneToMany(mappedBy = "InstructorCoursesInfo")
   private List<InstructorToCourses> instructorToCourseInfo;

   @OneToMany(mappedBy = "enrollment")
   private List<EnrollmentsId> enrollmentsIds;
}
