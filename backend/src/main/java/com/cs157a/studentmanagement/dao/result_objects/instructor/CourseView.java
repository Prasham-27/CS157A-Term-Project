package com.cs157a.studentmanagement.dao.result_objects.instructor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Instructor view of a base course that they are not assigned to
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseView {

   @JsonProperty("course_id")
   private Integer courseId;

   @JsonProperty("course_num")
   private Integer courseNum;

   @JsonProperty("course_name")
   private String courseName;

   @JsonProperty("points")
   private Double points;

   @JsonProperty("dept_abbreviation")
   private String deptAbbreviation;

   public CourseView(
           Integer courseId,
           Integer courseNum,
           String courseName,
           Double points,
           String deptAbbreviation
   ) {
      this.courseId = courseId;
      this.courseNum = courseNum;
      this.courseName = courseName;
      this.points = points;
      this.deptAbbreviation = deptAbbreviation;
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

   public String getDeptAbbreviation() {
      return deptAbbreviation;
   }

   public void setDeptAbbreviation(String deptAbbreviation) {
      this.deptAbbreviation = deptAbbreviation;
   }
}
