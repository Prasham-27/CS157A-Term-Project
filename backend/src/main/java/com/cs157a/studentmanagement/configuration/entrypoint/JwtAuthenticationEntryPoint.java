package com.cs157a.studentmanagement.configuration.entrypoint;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Catches the authorization exception so that our application can handle
 * expired JWT tokens.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

   @Override
   public void commence(
           HttpServletRequest request,
           HttpServletResponse response,
           AuthenticationException authException
   )
           throws IOException {

      response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
              "Unauthorized: Token expired or invalid");
   }
}
