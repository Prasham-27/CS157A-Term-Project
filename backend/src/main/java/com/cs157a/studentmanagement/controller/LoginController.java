/**
 * The API endpoints to signup different roles
 */

package com.cs157a.studentmanagement.controller;

import com.cs157a.studentmanagement.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.http.HttpSession;

import com.cs157a.studentmanagement.utils.enums.Role;

import java.util.Map;


@Controller
@RequestMapping("/api")
public class LoginController {

   @Autowired
   private UsersService usersService;

   // Handle login form submission and create a session attribute
   @PostMapping("/login/student")
   public ResponseEntity<String> studentLogin(@RequestBody Map<String, Object> request,
                                              HttpSession session) {
      return loginUser(request, Role.STUDENT, session);
   }

   // Handle login form submission and create a session attribute
   @PostMapping("/login/instructor")
   public ResponseEntity<String> instructorLogin(@RequestBody Map<String, Object> request,
                                                 HttpSession session) {
      return loginUser(request, Role.INSTRUCTOR, session);
   }

   @GetMapping("/logout")
   public ResponseEntity<String> logout(HttpSession session) {
      session.invalidate();
      return ResponseEntity.ok("Success");
   }

   /**
    * Helper function that logs in user based on the role we desire
    *
    * @param request   Holds the userId and password
    * @param session   The current session
    * @return          200 = success, 400 = fail
    */
   private ResponseEntity<String> loginUser(@RequestBody Map<String, Object> request,
                                            Role role, HttpSession session) {

      String password = (String)request.get("password");
      Long userId = Long.parseLong((String)request.get("user_id"));

      // Check if the password matches
      if (!usersService.checkPassword(password, userId) ||
              !usersService.roleIsEqual(userId, role))
         return ResponseEntity.badRequest().body("Invalid ID, password, or role");

      // Store username in session
      session.setAttribute("id", userId);
      session.setAttribute("role", role);

      return ResponseEntity.ok("Success");
   }
}