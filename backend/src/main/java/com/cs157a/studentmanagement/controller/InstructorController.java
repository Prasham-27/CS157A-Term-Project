package com.cs157a.studentmanagement.controller;

import com.cs157a.studentmanagement.dao.result_objects.instructor.InstructorCourse;
import com.cs157a.studentmanagement.dao.result_objects.instructor.StudentView;
import com.cs157a.studentmanagement.dao.result_objects.student.Course;
import com.cs157a.studentmanagement.dao.result_objects.student.StudentCourse;
import com.cs157a.studentmanagement.model.InstructorToCourses;
import com.cs157a.studentmanagement.service.DepartmentAndCoursesService;
import com.cs157a.studentmanagement.service.InstructorsService;
import com.cs157a.studentmanagement.service.StudentsService;
import com.cs157a.studentmanagement.utils.enums.Days;
import com.cs157a.studentmanagement.utils.enums.Grades;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/instructor")
public class InstructorController {

   private final InstructorsService instructorsService;
   private final DepartmentAndCoursesService departmentAndCoursesService;
   private final ObjectMapper objectMapper;

   public InstructorController(
           InstructorsService instructorsService,
           DepartmentAndCoursesService departmentAndCoursesService,
           ObjectMapper objectMapper
   ) {
      this.instructorsService = instructorsService;
      this.departmentAndCoursesService = departmentAndCoursesService;
      this.objectMapper = objectMapper;
   }

   /**
    * @param session
    * @return   All active and inactive courses in two separate lists
    */
   @GetMapping("/mycourses")
   public ResponseEntity<String> instructorMyCourses(HttpSession session) {

      // Get the student's ID
      Integer instructorId = getInstructorId();

      // Get Active courses
      List<InstructorCourse> activeCourses =
              instructorsService.getAllActiveCourses(instructorId);

      List<InstructorCourse> inactiveCourses =
              instructorsService.getAllInactiveCourses(instructorId);

      Map<String, Object> map = new HashMap<>();
      map.put("active_courses", activeCourses);
      map.put("inactive_courses", inactiveCourses);

      // Convert to json
      String json = "";
      try {
         json = objectMapper.writeValueAsString(map);
      }
      catch (JsonProcessingException e) {
         e.printStackTrace();
      }

      return ResponseEntity.ok(json);
   }

   @PostMapping("/mycourses/{instructor_course_id}/students")
   public ResponseEntity<String> searchInstructorCourseStudents(
           @PathVariable("instructor_course_id") Integer instructorCourseId,
           @RequestBody Map<String, Object> request
   ) {

      String searchTerm = (String)request.get("search_term");
      if (searchTerm != null && searchTerm.equals(""))
         searchTerm = null;

      // Get Active courses
      List<StudentView> students =
              instructorsService.getAllStudentsInCourse(
                      instructorCourseId,
                      (String)request.get("search_term")
              );

      // Convert to json
      String json = "";
      try {
         json = objectMapper.writeValueAsString(students);
      }
      catch (JsonProcessingException e) {
         e.printStackTrace();
      }

      return ResponseEntity.ok(json);
   }

   /**
    * @param session
    * @return        Gets the basic courses from the department that the
    *                instructor is from.
    */
   @GetMapping("/courses")
   public ResponseEntity<String> getCourses(HttpSession session) {

      // Get the student's ID
      Integer instructorId = getInstructorId();

      // Get Active courses
      List<Course> courses =
              instructorsService.getAllCoursesInInstructorDepartment(instructorId);

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
    * @return        Gets the basic courses from the department that the
    *                instructor is from.
    */
   @PostMapping("/courses/{course_id}/new-course")
   public ResponseEntity<String> getCourses(
           @PathVariable("course_id") Integer courseId,
           @RequestBody Map<String, Object> request
   ) {
      // Create a new instructor to course object
      InstructorToCourses instructorCourse = new InstructorToCourses();

      instructorCourse.setCourseId(courseId);

      // Check if max enrollment is between 15 and 60 inclusive
      if (!instructorCourse.setMaxEnrollment((Integer)request.get("max_enrollment")))
         return ResponseEntity.badRequest().body("Max enrollment not between 15 and 60 inclusive");

      // Check if the time range is 75 minutes
      Time startTime = Time.valueOf((String)request.get("max_enrollment"));
      Time endTime = Time.valueOf((String)request.get("max_enrollment"));
      if (!instructorCourse.setTime(startTime, endTime))
         return ResponseEntity.badRequest().body("Invalid time range, should be 75 minutes long");

      // Check for duplicate days, we are expecting a list of days sent as strings
      List<Days> daysList = ((List<String>) request.get("days")).stream()
              .map(day -> Days.valueOf(day.toUpperCase()))
              .collect(Collectors.toList());
      if (!instructorCourse.setDays(daysList))
         return ResponseEntity.badRequest().body("Duplicate days found");

      // Add new course
      instructorsService.createInstructorCourse(instructorCourse);

      return ResponseEntity.ok("Add successful!");
   }

   @PostMapping("/mycourses/{instructor_course_id}")
   public ResponseEntity<String> completeCourse(
           @PathVariable("instructor_course_id") Integer instructorCourseId,
           @RequestBody List<Map<String, Object>> request
   ) {
      List<StudentView> students = new ArrayList<>();
      for (Map<String, Object> map : request) {

         String grade = (String)map.get("grade");

         if (grade.equals(""))
            return ResponseEntity.badRequest().body("One of the grades is empty");

         students.add(
                 new StudentView((Integer)map.get("student_id"), Grades.valueOf(grade))
         );
      }

      // Complete the course
      instructorsService.completeCourse(students, instructorCourseId);

      return ResponseEntity.ok("Add successful!");
   }


   /**
    * A helper function to get the instructor's ID. We know they will be a
    * instructor because they couldn't use the endpoint otherwise.
    *
    * @return The student's ID
    */
   private static Integer getInstructorId() {
      Authentication authentication =
              SecurityContextHolder.getContext().getAuthentication();
      UserDetails userDetails = (UserDetails)authentication.getPrincipal();
      return Integer.parseInt(userDetails.getUsername());
   }
}
