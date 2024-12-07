package com.cs157a.studentmanagement.controller;

import com.cs157a.studentmanagement.service.UsersService;
import com.cs157a.studentmanagement.utils.enums.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.Map;

/**
 * Handles the signup operations for the frontend
 */
@Controller
@RequestMapping("/api")
public class SignUpController {

   private final UsersService usersService;

   public SignUpController(UsersService usersService) {
      this.usersService = usersService;
   }

   /**
    * Signs up a user as a student
    *
    * @param request The data required to signup the user
    * @param session The session
    * @return        The Success (200) or fail (400) response
    */
   @PostMapping("/signup/student")
   public ResponseEntity<String> signupStudent(@RequestBody Map<String, Object> request,
                                       HttpSession session) {
      return signupUser(request, Role.STUDENT);
   }

   /**
    * Signs up a user as an instructor
    *
    * @param request The data required to signup the user
    * @param session The session
    * @return        The Success (200) or fail (400) response
    */
   @PostMapping("/signup/instructor")
   public ResponseEntity<String> signupInstructor(@RequestBody Map<String, Object> request,
                                        HttpSession session) {
      return signupUser(request, Role.INSTRUCTOR);
   }

   /**
    * Helper method that signs up a user to a specific Role.
    *
    * @param request The request body of signup
    * @param role    The role to signup the user
    * @return        The Success (200) or fail (400) response
    */
   public ResponseEntity<String> signupUser(Map<String, Object> request, Role role) {

      // Unpack the json response
      String email = (String)request.get("email");
      String passwordHash = (String)request.get("password");
      String firstName  = (String)request.get("first_name");
      String lastName = (String)request.get("last_name");

      try {
         // Attempt signup
         if (!usersService.signUp(
                 email,
                 passwordHash,
                 firstName,
                 lastName,
                 role
         )) {
            return ResponseEntity.badRequest().body(
                    String.format("Signup failed: Invalid data or error processing signup for %s",
                            role.toString()));
         }
      }
      catch (Exception e) {
         e.printStackTrace();
      }

      return ResponseEntity.ok("Success");
   }
}