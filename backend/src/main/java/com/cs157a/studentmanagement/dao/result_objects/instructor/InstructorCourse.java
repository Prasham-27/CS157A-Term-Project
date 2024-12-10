package com.cs157a.studentmanagement.dao.result_objects.instructor;

import com.cs157a.studentmanagement.utils.enums.Days;
import com.cs157a.studentmanagement.utils.enums.EnrollmentStatus;
import com.cs157a.studentmanagement.utils.enums.Grades;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

/**
 * The course of the instructor for instructor mycourses
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstructorCourse {

   @JsonProperty("instructor_course_id")
   private Integer instructorCourseId;

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

   @JsonProperty("max_enrollment")
   private Integer maxEnrollment;

   @JsonProperty("num_enrolled")
   private Integer numEnrolled;

   public InstructorCourse(
           Integer instructorCourseId,
           String courseName,
           Integer courseNumber,
           String deptAbbreviation,
           Double points,
           Time startTime,
           Time endTime,
           List<Days> courseDays,
           Integer maxEnrollment,
           Integer numEnrolled
   ) {
      this.instructorCourseId = instructorCourseId;
      this.courseName = courseName;
      this.courseNumber = courseNumber;
      this.deptAbbreviation = deptAbbreviation;
      this.points = points;
      this.startTime = startTime;
      this.endTime = endTime;
      this.courseDays = courseDays;
      this.maxEnrollment = maxEnrollment;
      this.numEnrolled = numEnrolled;
   }

   public InstructorCourse(
           Integer instructorCourseId,
           String courseName,
           Integer courseNumber,
           String deptAbbreviation,
           Double points,
           Time startTime,
           Time endTime,
           List<Days> courseDays
   ) {
      this.instructorCourseId = instructorCourseId;
      this.courseName = courseName;
      this.courseNumber = courseNumber;
      this.deptAbbreviation = deptAbbreviation;
      this.points = points;
      this.startTime = startTime;
      this.endTime = endTime;
      this.courseDays = courseDays;
   }

   public Integer getInstructorCourseId() {
      return instructorCourseId;
   }

   public void setInstructorCourseId(Integer instructorCourseId) {
      this.instructorCourseId = instructorCourseId;
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
}
