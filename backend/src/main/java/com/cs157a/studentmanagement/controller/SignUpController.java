/**
 * The API endpoints to signup different roles
 */

package com.cs157a.studentmanagement.controller;

import com.cs157a.studentmanagement.service.RolesService;
import com.cs157a.studentmanagement.service.UsersService;
import com.cs157a.studentmanagement.utils.enums.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.http.HttpSession;

import java.util.Map;


@Controller
@RequestMapping("/api")
public class SignUpController {

   @Autowired
   private UsersService usersService;

   @Autowired
   private RolesService rolesService;

   // TODO will later need to check the role for signing up students

   @PostMapping("/signup/student")
   public ResponseEntity<String> signupStudent(@RequestBody Map<String, Object> request,
                                       HttpSession session) {
      return signupUser(request, Role.STUDENT);
   }

   @PostMapping("/signup/instructor")
   public ResponseEntity<String> signupInstructor(@RequestBody Map<String, Object> request,
                                        HttpSession session) {
      return signupUser(request, Role.INSTRUCTOR);
   }

   /**
    * Signs up a user to a specific Role.
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
                 rolesService.getRoleId(role.toString())
         )) {
            return ResponseEntity.badRequest().body(
                    String.format("Signup failed: Invalid data or error processing signup for %s",
                            role.toString()));
         }
      }
      catch (Exception e) {
         e.printStackTrace();
         System.out.printf("Failed to sign-up user of role %s\n", role.toString());
      }

      return ResponseEntity.ok("Success");
   }
}