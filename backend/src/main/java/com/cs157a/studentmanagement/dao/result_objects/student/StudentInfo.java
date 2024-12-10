package com.cs157a.studentmanagement.dao.result_objects.student;

import com.cs157a.studentmanagement.utils.enums.AcademicYear;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Student Info object that only the students can see
 */
public class StudentInfo {

   @JsonProperty("major")
   private Integer StudentId;

   @JsonProperty("major")
   private String major;

   @JsonProperty("academic_year")
   private AcademicYear academicYear;

   @JsonProperty("first_name")
   private String firstName;

   @JsonProperty("last_name")
   private String lastName;

   @JsonProperty("email")
   private String email;

   public StudentInfo(
           Integer studentId,
           String major,
           AcademicYear academicYear,
           String firstName,
           String lastName,
           String email
   ) {
      StudentId = studentId;
      this.major = major;
      this.academicYear = academicYear;
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
   }

   public Integer getStudentId() {
      return StudentId;
   }

   public void setStudentId(Integer studentId) {
      StudentId = studentId;
   }

   public String getMajor() {
      return major;
   }

   public void setMajor(String major) {
      this.major = major;
   }

   public AcademicYear getAcademicYear() {
      return academicYear;
   }

   public void setAcademicYear(AcademicYear academicYear) {
      this.academicYear = academicYear;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }
}
