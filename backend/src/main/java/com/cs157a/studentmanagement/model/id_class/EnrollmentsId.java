package com.cs157a.studentmanagement.model.id_class;

import java.io.Serializable;
import java.util.Objects;

public class EnrollmentsId implements Serializable {
   private Long studentId;
   private Long instructorId;
   private Long courseId;

   // Constructors, getters, setters, equals(), and hashCode()

   public EnrollmentsId() {}

   public EnrollmentsId(Long studentId, Long instructorId, Long courseId) {
      this.studentId = studentId;
      this.courseId = courseId;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      EnrollmentsId that = (EnrollmentsId) o;
      return studentId.equals(that.studentId) && courseId.equals(that.courseId)
              && instructorId.equals(that.instructorId);
   }

   @Override
   public int hashCode() {
      return Objects.hash(studentId, instructorId, courseId);
   }
}
