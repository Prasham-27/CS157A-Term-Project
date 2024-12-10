package com.cs157a.studentmanagement.dao.result_objects.instructor;

import com.cs157a.studentmanagement.utils.enums.Grades;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data that instructors can view about students
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentView {

   @JsonProperty("student_id")
   private Integer studentId;

   @JsonProperty("first_name")
   private String firstName;

   @JsonProperty("last_name")
   private String lastName;

   @JsonProperty("email")
   private String email;

   @JsonProperty("major")
   private String major;

   @JsonProperty("grade")
   private Grades grade;

   public StudentView(
           Integer studentId,
           String firstName,
           String lastName,
           String email,
           String major
   ) {
      this.studentId = studentId;
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.major = major;
   }

   public StudentView(
           Integer studentId,
           Grades grade
   ) {
      this.studentId = studentId;
      this.grade = grade;
   }


   public Integer getStudentId() {
      return studentId;
   }

   public void setStudentId(Integer studentId) {
      this.studentId = studentId;
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

   public String getMajor() {
      return major;
   }

   public void setMajor(String major) {
      this.major = major;
   }

   public Grades getGrade() {
      return grade;
   }

   public void setGrade(Grades grade) {
      this.grade = grade;
   }
}
