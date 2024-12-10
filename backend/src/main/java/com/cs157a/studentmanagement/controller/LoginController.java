package com.cs157a.studentmanagement.controller;

import com.cs157a.studentmanagement.service.JwtService;
import com.cs157a.studentmanagement.service.UserDetailsServiceImpl;
import com.cs157a.studentmanagement.service.UsersService;
import com.cs157a.studentmanagement.utils.ResponseHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.*;


/**
 * Handles the login API requests
 */
@Controller
@RequestMapping("/api")
public class LoginController {

   private UsersService usersService;

   private UserDetailsServiceImpl userDetailsService;

   @Autowired
   private AuthenticationManager authenticationManager;

   private final JwtService jwtService;

   public LoginController(
           UsersService usersService,
           UserDetailsServiceImpl userDetails,
           JwtService jwtService
   ) {
      this.usersService = usersService;
      this.userDetailsService = userDetails;
      this.jwtService = jwtService;
   }

   /**
    * Logs out a user by destroying their session
    *
    * @return        200 = success
    */
   @GetMapping("/logout")
   public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {

      // TODO remove this, this does nothing for JWT tokens
      HttpSession session = request.getSession(false);
      if (session != null) {
         session.invalidate();
      }
      SecurityContextHolder.getContext().setAuthentication(null);

      return ResponseEntity.ok("Success");
   }

   /**
    * Logs in the user and states their session
    *
    * @param request   Holds the userId and password
    * @param session   The current session
    * @return          200 = success, 400 = fail, and the path the
    *                  user should be redirected to
    */
   @PostMapping("/login")
   public ResponseEntity<String> loginUser(@RequestBody Map<String, Object> request,
                                            HttpSession session) {

      String password = (String)request.get("password");
      Long userId = Long.parseLong((String)request.get("user_id"));

      // Check if the password matches
      if (userId == null || password == null || !usersService.checkPassword(password, userId))
         return ResponseEntity.badRequest().body("Invalid ID, or password");

      String jwtToken;
      String roleName = usersService.getRoleName(userId);

      try {

         // Get the user details
         UserDetails userDetails =
                 userDetailsService.loadUserByUsername(userId.toString());

         // Generate JWT token for authenticated user
         jwtToken = jwtService.generateToken(userDetails);
      }
      catch (BadCredentialsException e) {
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid ID, or password");
      }

      return ResponseHelper.createJsonResponse(Map.of(
              "token", jwtToken,
              "expiration", jwtService.getExpirationTime(),
              "message", "Login Success!",
              "status", 200,
              "redirect", String.format("/%s", roleName.toLowerCase())
      ));
   }
}