package com.cs157a.studentmanagement.model;

import com.cs157a.studentmanagement.model.id_class.InstructorCoursesInfoId;
import com.cs157a.studentmanagement.utils.enums.Days;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.sql.Time;
import java.util.HashSet;
import java.util.List;

/**
 * Represents InstructorToCourses table, setting its attributes will
 * check if they are valid
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstructorToCourses {

   @JsonProperty("instructor_to_courses_id")
   private Integer instructorToCoursesId;

   @JsonProperty("instructor_id")
   private Integer instructorId;

   @JsonProperty("course_id")
   private Integer courseId;

   @JsonProperty("max_enrollment")
   private Integer maxEnrollment;

   @JsonProperty("num_enrolled")
   private Integer numEnrolled;

   @JsonProperty("start_time")
   private Time startTime;

   @JsonProperty("end_time")
   private Time endTime;

   @JsonProperty("days")
   private List<Days> days;

   public Integer getCourseId() {
      return courseId;
   }

   public void setCourseId(Integer courseId) {
      this.courseId = courseId;
   }

   public Integer getInstructorId() {
      return instructorId;
   }

   public void setInstructorId(Integer instructorId) {
      this.instructorId = instructorId;
   }

   public Integer getInstructorToCoursesId() {
      return instructorToCoursesId;
   }

   public void setInstructorToCoursesId(Integer instructorToCoursesId) {
      this.instructorToCoursesId = instructorToCoursesId;
   }

   public Integer getMaxEnrollment() {
      return maxEnrollment;
   }

   /**
    * Makes sure that max enrollment is >= 15 and <= 60
    *
    * @param maxEnrollment
    */
   public boolean setMaxEnrollment(Integer maxEnrollment) {
      if (maxEnrollment.intValue() < 15 || maxEnrollment.intValue() > 60)
         return false;
      this.maxEnrollment = maxEnrollment;
      return true;
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

   /**
    * Checks that the start and end times make up 1 hour and 15 minutes and
    * aren't before or after each other.
    *
    * @param startTime
    */
   public boolean setTime(Time startTime, Time endTime) {

      // Check startTime before endTime
      if (!startTime.before(endTime))
         return false;

      // Make sure that together they are around 75 minutes
      long differenceInMillis = endTime.getTime() - startTime.getTime();
      long differenceInMinutes = differenceInMillis / (60 * 1000);
      if (differenceInMinutes != 75)
         return false;

      this.startTime = startTime;
      this.endTime = endTime;
      return true;
   }

   public Time getEndTime() {
      return endTime;
   }

   public List<Days> getDays() {
      return days;
   }

   /**
    * Makes sure that there are no duplicate days
    */
   public boolean setDays(List<Days> days) {
   HashSet<Days> daySet = new HashSet<>();
      for (Days day : days)
         if (!daySet.add(day))
            return false;
      this.days = days;
      return true;
   }
}
