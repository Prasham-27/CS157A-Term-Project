package com.cs157a.studentmanagement.model.id_class;

import java.util.Objects;

public class InstructorCoursesInfoId {

   private Long instructorId;
   private Long courseId;

   public InstructorCoursesInfoId() {}

   public InstructorCoursesInfoId(Long studentId, Long courseId) {
      this.instructorId = studentId;
      this.courseId = courseId;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      InstructorCoursesInfoId that = (InstructorCoursesInfoId) o;
      return instructorId.equals(that.instructorId) && courseId.equals(that.courseId);
   }

   @Override
   public int hashCode() {
      return Objects.hash(instructorId, courseId);
   }
}
