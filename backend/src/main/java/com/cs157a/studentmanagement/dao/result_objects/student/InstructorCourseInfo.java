package com.cs157a.studentmanagement.dao.result_objects.student;

import com.cs157a.studentmanagement.utils.enums.Days;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Time;
import java.util.List;

/**
 * The instructor data that the students will see for each course
 */
public class InstructorCourseInfo {

   @JsonProperty("instructor_course_id")
   private Integer instructorCourseId;

   @JsonProperty("first_name")
   private String firstName;

   @JsonProperty("last_name")
   private String lastName;

   @JsonProperty("max_enrollment")
   private Integer maxEnrollment;

   @JsonProperty("num_enrolled")
   private Integer numEnrolled;

   @JsonProperty("start_time")
   private Time startTime;

   @JsonProperty("end_time")
   private Time endTime;

   @JsonProperty("course_days")
   private List<Days> courseDays;

   public InstructorCourseInfo(
           Integer instructorCourseId,
           String firstName,
           String lastName,
           Integer maxEnrollment,
           Integer numEnrolled,
           Time startTime,
           Time endTime,
           List<Days> courseDays
   ) {
      this.instructorCourseId = instructorCourseId;
      this.firstName = firstName;
      this.lastName = lastName;
      this.maxEnrollment = maxEnrollment;
      this.numEnrolled = numEnrolled;
      this.startTime = startTime;
      this.endTime = endTime;
      this.courseDays = courseDays;
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

   public Integer getMaxEnrollment() {
      return maxEnrollment;
   }

   public void setMaxEnrollment(Integer maxEnrollment) {
      this.maxEnrollment = maxEnrollment;
   }

   public Integer getNumEnrolled() {
      return numEnrolled;
   }

   public void setNumEnrolled(Integer numEnrolled) {
      this.numEnrolled = numEnrolled;
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
