package com.cs157a.studentmanagement.dao.result_objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An object that represents a department in the table
 */
public class Department {

   @JsonProperty("dept_id")
   private Integer deptId;

   @JsonProperty("dept_name")
   private String deptName;

   @JsonProperty("dept_abbreviation")
   private String deptAbbreviation;

   public Department(Integer deptId, String deptName, String deptAbbreviation) {
      this.deptId = deptId;
      this.deptName = deptName;
      this.deptAbbreviation = deptAbbreviation;
   }

   public Integer getDeptId() {
      return deptId;
   }

   public void setDeptId(Integer deptId) {
      this.deptId = deptId;
   }

   public String getDeptName() {
      return deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getDeptAbbreviation() {
      return deptAbbreviation;
   }

   public void setDeptAbbreviation(String deptAbbreviation) {
      this.deptAbbreviation = deptAbbreviation;
   }
}
