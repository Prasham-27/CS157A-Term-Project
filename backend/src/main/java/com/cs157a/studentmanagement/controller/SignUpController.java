package com.cs157a.studentmanagement.controller;

import com.cs157a.studentmanagement.service.RolesService;
import com.cs157a.studentmanagement.service.UsersService;
import com.cs157a.studentmanagement.utils.enums.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;

import jakarta.servlet.http.HttpSession;

import java.util.Map;


@Controller
@RequestMapping("/api")
public class SignUpController {

   @Autowired
   private UsersService usersService;

   @Autowired
   private RolesService rolesService;

   // Handle login form submission and create a session attribute
   // TODO Make a different signup for each role
   @PostMapping("/signup")
   public ResponseEntity<String> signup(@RequestBody Map<String, Object> request,
                                       HttpSession session) {

      // TODO give this a more descriptive response, and don't always create a Student
      if (!usersService.signUp((String)request.get("email"),
              (String)request.get("password"), (String)request.get("firstName"),
              (String)request.get("lastName"),
              rolesService.getRoleId(Role.STUDENT.toString()))) {
         return ResponseEntity.badRequest().body("Signup failed: Invalid data or error processing signup");
      }

      return ResponseEntity.ok("Success");
   }
}