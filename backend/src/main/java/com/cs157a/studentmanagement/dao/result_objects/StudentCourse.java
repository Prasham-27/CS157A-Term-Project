package com.cs157a.studentmanagement.dao.result_objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.cs157a.studentmanagement.utils.enums.Days;
import com.cs157a.studentmanagement.utils.enums.EnrollmentStatus;
import com.cs157a.studentmanagement.utils.enums.Grades;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

/**
 * Represents the ENROLLED, DROPPED, and COMPLETED courses for a student.
 * Will only include NON-NULL attributes when sent to client as json.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentCourse {

   @JsonProperty("instructor_first_name")
   private String instructorFirstName;

   @JsonProperty("instructor_last_name")
   private String instructorLastName;

   @JsonProperty("course_name")
   private String courseName;

   @JsonProperty("course_number")
   private Integer courseNumber;

   @JsonProperty("dept_abbreviation")
   private String deptAbbreviation;

   @JsonProperty("points")
   private Double points;

   @JsonProperty("start_time")
   private Time startTime;

   @JsonProperty("end_time")
   private Time endTime;

   @JsonProperty("course_days")
   private List<Days> courseDays;

   @JsonProperty("enrollment_date")
   private Timestamp enrollmentDate;

   @JsonProperty("enrollment_status")
   private EnrollmentStatus enrollmentStatus;

   @JsonProperty("grade")
   private Grades grade;

   public StudentCourse(
           String instructorFirstName,
           String instructorLastName,
           String courseName,
           Integer courseNumber,
           String deptAbbreviation,
           Double points,
           Time startTime,
           Time endTime,
           List<Days> courseDays,
           Timestamp enrollmentDate,
           EnrollmentStatus enrollmentStatus,
           Grades grade) {
      this.instructorFirstName = instructorFirstName;
      this.instructorLastName = instructorLastName;
      this.courseName = courseName;
      this.courseNumber = courseNumber;
      this.deptAbbreviation = deptAbbreviation;
      this.points = points;
      this.startTime = startTime;
      this.endTime = endTime;
      this.courseDays = courseDays;
      this.enrollmentDate = enrollmentDate;
      this.enrollmentStatus = enrollmentStatus;
      this.grade = grade;
   }

   public String getInstructorFirstName() {
      return instructorFirstName;
   }

   public void setInstructorFirstName(String instructorFirstName) {
      this.instructorFirstName = instructorFirstName;
   }

   public String getInstructorLastName() {
      return instructorLastName;
   }

   public void setInstructorLastName(String instructorLastName) {
      this.instructorLastName = instructorLastName;
   }

   public String getCourseName() {
      return courseName;
   }

   public void setCourseName(String courseName) {
      this.courseName = courseName;
   }

   public Integer getCourseNumber() {
      return courseNumber;
   }

   public void setCourseNumber(Integer courseNumber) {
      this.courseNumber = courseNumber;
   }

   public String getDeptAbbreviation() {
      return deptAbbreviation;
   }

   public void setDeptAbbreviation(String deptAbbreviation) {
      this.deptAbbreviation = deptAbbreviation;
   }

   public Double getPoints() {
      return points;
   }

   public void setPoints(Double points) {
      this.points = points;
   }

   public Time getStartTime() {
      return startTime;
   }

   public void setStartTime(Time startTime) {
      this.startTime = startTime;
   }

   public Time getEndTime() {
      return endTime;
   }

   public void setEndTime(Time endTime) {
      this.endTime = endTime;
   }

   public List<Days> getCourseDays() {
      return courseDays;
   }

   public void setCourseDays(List<Days> courseDays) {
      this.courseDays = courseDays;
   }

   public Timestamp getEnrollmentDate() {
      return enrollmentDate;
   }

   public void setEnrollmentDate(Timestamp enrollmentDate) {
      this.enrollmentDate = enrollmentDate;
   }

   public EnrollmentStatus getEnrollmentStatus() {
      return enrollmentStatus;
   }

   public void setEnrollmentStatus(EnrollmentStatus enrollmentStatus) {
      this.enrollmentStatus = enrollmentStatus;
   }

   public Grades getGrade() {
      return grade;
   }

   public void setGrade(Grades grade) {
      this.grade = grade;
   }
}
