package com.cs157a.studentmanagement.model;

import com.cs157a.studentmanagement.model.id_class.EnrollmentsId;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "courses", uniqueConstraints = @UniqueConstraint(columnNames = {"dept", "course_num"}))
public class Courses {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "course_id")
   private Long id;

   @ManyToOne
   @JoinColumn(name = "dept_id", nullable = false) // CONSTRAINT Foreign key
   private Departments dept;

   @Column(name = "course_num", nullable = false)
   private Short courseNum;

   @OneToOne(mappedBy = "CourseInfo")
   private CourseInfo info;

   @OneToMany(mappedBy = "InstructorCoursesInfo")
   private List<InstructorCoursesInfo> instructorToCourseInfo;

   @OneToMany(mappedBy = "enrollment")
   private List<EnrollmentsId> enrollmentsIds;
}
