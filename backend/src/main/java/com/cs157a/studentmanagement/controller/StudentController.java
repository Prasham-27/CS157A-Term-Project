package com.cs157a.studentmanagement.controller;


import com.cs157a.studentmanagement.dao.result_objects.*;
import com.cs157a.studentmanagement.service.DepartmentAndCoursesService;
import com.cs157a.studentmanagement.service.StudentsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/student")
public class StudentController {

   private final StudentsService studentsService;
   private final DepartmentAndCoursesService departmentAndCoursesService;
   private final ObjectMapper objectMapper;

   public StudentController(
           StudentsService studentsService,
           ObjectMapper objectMapper,
           DepartmentAndCoursesService departmentAndCoursesService
   ) {
      this.studentsService = studentsService;
      this.objectMapper = objectMapper;
      this.departmentAndCoursesService = departmentAndCoursesService;
   }

   /**
    * @param session
    * @return        Student profile info such as name, email, major, etc
    */
   @GetMapping("/info")
   public ResponseEntity<String> getStudentInfo(HttpSession session) {
      StudentInfo studentInfo = studentsService.getProfile(getStudentId());

      // Convert to json
      String json = "";
      try {
         json = objectMapper.writeValueAsString(studentInfo);
      }
      catch (JsonProcessingException e) {
         e.printStackTrace();
      }

      return ResponseEntity.ok(json);
   }

   /**
    * @param session
    * @return   All student courses that aren't COMPLETED
    */
   @GetMapping("/mycourses")
   public ResponseEntity<String> studentMyCourses(HttpSession session) {

      // Get the student's ID
      Integer studentId = getStudentId();

      // Get the student's ENROLLED and DROPPED courses
      List<StudentCourse> myCourses = studentsService.getIncompleteCourses(studentId);

      // Convert to json
      String json = "";
      try {
         json = objectMapper.writeValueAsString(myCourses);
      }
      catch (JsonProcessingException e) {
         e.printStackTrace();
      }

      return ResponseEntity.ok(json);
   }

   /**
    * @param session The session
    * @return        Gets the completed courses of grades of the student that
    *                calls it.
    */
   @GetMapping("/grades")
   public ResponseEntity<String> studentGrades(HttpSession session) {

      // Get the student's ID
      Integer studentId = getStudentId();

      // Get the student's ENROLLED and DROPPED courses
      List<StudentCourse> myCourses = studentsService.getCompleteCourses(studentId);
      Double gpa = studentsService.getGpa(studentId);

      // Create a map to hold the data
      Map<String, Object> responseMap = new HashMap<>();
      responseMap.put("courses", myCourses);
      responseMap.put("gpa", gpa);

      // Convert to json
      String json = "";
      try {
         json = objectMapper.writeValueAsString(responseMap);
      }
      catch (JsonProcessingException e) {
         e.printStackTrace();
      }

      return ResponseEntity.ok(json);
   }

   /**
    * @param session
    * @return  All departments in json, including the id, name, and abbreviation
    */
   @GetMapping("/departments")
   public ResponseEntity<String> studentGetDepartments(HttpSession session) {

      // Get the list of all departments
      List<Department> depts = departmentAndCoursesService.getAllDepartments();

      // Convert to json
      String json = "";
      try {
         json = objectMapper.writeValueAsString(depts);
      }
      catch (JsonProcessingException e) {
         e.printStackTrace();
      }

      return ResponseEntity.ok(json);
   }

   /**
    * @param deptId The ID of the department where we get the courses from
    * @return       The list of the courses in the department
    */
   @GetMapping("/departments/{dept_id}/courses")
   public ResponseEntity<String> getCoursesByDepartment(@PathVariable("dept_id") Integer deptId) {
      List<Course> courses =
              departmentAndCoursesService.getAllCoursesFromDepartment(deptId);

      // Convert to json
      String json = "";
      try {
         json = objectMapper.writeValueAsString(courses);
      }
      catch (JsonProcessingException e) {
         e.printStackTrace();
      }

      return ResponseEntity.ok(json);
   }

   @GetMapping("/departments/{dept_id}/courses/{course_id}")
   public ResponseEntity<String> getCourseInstructorDetails(
           @PathVariable("dept_id") Integer deptId,
           @PathVariable("course_id") Integer courseId
   ) {
      // Check that the dept_ids match
      if (departmentAndCoursesService.getCourseDeptId(courseId) != deptId)
         return ResponseEntity.badRequest().body("Course is not of this department");

      // Get the courses
      List<InstructorCourseInfo> courses =
              departmentAndCoursesService.getInstructorCourseInfo(courseId);

      // Convert to json
      String json = "";
      try {
         json = objectMapper.writeValueAsString(courses);
      }
      catch (JsonProcessingException e) {
         e.printStackTrace();
      }

      return ResponseEntity.ok(json);
   }

   /**
    * A helper function to get the student's ID. We know they will be a
    * student because they couldn't use the endpoint otherwise.
    *
    * @return The student's ID
    */
   private static Integer getStudentId() {
      Authentication authentication =
              SecurityContextHolder.getContext().getAuthentication();
      UserDetails userDetails = (UserDetails)authentication.getPrincipal();
      return Integer.parseInt(userDetails.getUsername());
   }
}
