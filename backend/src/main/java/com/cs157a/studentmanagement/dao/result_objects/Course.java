package com.cs157a.studentmanagement.dao.result_objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Holds basic course data from the courses and course_info tables
 */
public class Course {

   @JsonProperty("course_id")
   private Integer courseId;

   @JsonProperty("course_num")
   private Integer courseNum;

   @JsonProperty("course_name")
   private String courseName;

   @JsonProperty("points")
   private Double points;

   public Course(Integer courseId, Integer courseNum, String courseName, Double points) {
      this.courseId = courseId;
      this.courseNum = courseNum;
      this.courseName = courseName;
      this.points = points;
   }

   public Integer getCourseId() {
      return courseId;
   }

   public void setCourseId(Integer courseId) {
      this.courseId = courseId;
   }

   public Integer getCourseNum() {
      return courseNum;
   }

   public void setCourseNum(Integer courseNum) {
      this.courseNum = courseNum;
   }

   public String getCourseName() {
      return courseName;
   }

   public void setCourseName(String courseName) {
      this.courseName = courseName;
   }

   public Double getPoints() {
      return points;
   }

   public void setPoints(Double points) {
      this.points = points;
   }
}
