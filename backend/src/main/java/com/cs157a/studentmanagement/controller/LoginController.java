package com.cs157a.studentmanagement.controller;

import com.cs157a.studentmanagement.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.http.HttpSession;

import com.cs157a.studentmanagement.utils.enums.Role;


@Controller
@RequestMapping("/api")
public class LoginController {

   @Autowired
   private UsersService usersService;

   // Handle login form submission and create a session attribute
   @PostMapping("/login/student")
   public ResponseEntity<String> studentLogin(@RequestParam Long userId,
                                       @RequestParam String password, HttpSession session) {
      return loginUser(userId, password, Role.STUDENT, session);
   }

   // Handle login form submission and create a session attribute
   @PostMapping("/login/instructor")
   public ResponseEntity<String> instructorLogin(@RequestParam Long userId,
                                       @RequestParam String password, HttpSession session) {
      return loginUser(userId, password, Role.INSTRUCTOR, session);
   }

   @GetMapping("/logout")
   public String logout(HttpSession session) {
      session.invalidate();
      return "redirect:/login";
   }

   /**
    * Helper function that logs in user based on the role we desire
    *
    * @param userId
    * @param password
    * @param session
    * @return
    */
   private ResponseEntity<String> loginUser(
           Long userId, String password, Role role, HttpSession session) {

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