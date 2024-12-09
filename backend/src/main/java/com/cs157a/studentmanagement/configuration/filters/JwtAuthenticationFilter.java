package com.cs157a.studentmanagement.configuration.filters;

import com.cs157a.studentmanagement.service.JwtService;
import com.cs157a.studentmanagement.service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

   private final UserDetailsService userDetailsService;
   private final HandlerExceptionResolver handlerExceptionResolver;
   private final JwtService jwtService;

   public JwtAuthenticationFilter(
           JwtService jwtService,
           HandlerExceptionResolver handlerExceptionResolver,
           UserDetailsServiceImpl userDetailsService
   ) {
      this.jwtService = jwtService;
      this.handlerExceptionResolver = handlerExceptionResolver;
      this.userDetailsService = userDetailsService;
   }

   /**
    * The per request JWT filter
    *
    * @param request
    * @param response
    * @param filterChain
    * @throws ServletException
    * @throws IOException
    */
   @Override
   public void doFilterInternal(@NonNull HttpServletRequest request,
                                @NonNull HttpServletResponse response,
                                @NonNull FilterChain filterChain)
   throws ServletException, IOException {

      final String authHeader = request.getHeader("Authorization");

      // Check if auth header is valid, if not move to next link
      if (authHeader == null || !authHeader.startsWith("Bearer ")) {
         filterChain.doFilter(request, response);
         return;
      }

      try {

         // Get jwt token and user's primary key
         final String jwt = authHeader.substring(7);

         // Check if token is expired
         if (jwtService.isTokenExpired(jwt)) {
            // Token expired, return 401 Unauthorized response
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Token invalid");
            return;
         }

         final String userId = jwtService.extractUsername(jwt);

         // Get the Spring authentication data
         Authentication authentication =
                 SecurityContextHolder.getContext().getAuthentication();

         // If authorization cookie is empty
         if (userId != null && authentication == null) {
            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(userId);

            // Get user details if jwt token is valid
            if (jwtService.isTokenValid(jwt, userDetails)) {
               UsernamePasswordAuthenticationToken authToken =
                       new UsernamePasswordAuthenticationToken(
                       userDetails,
                       null,
                       userDetails.getAuthorities()
               );

               // Set the auth data
               authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               SecurityContextHolder.getContext().setAuthentication(authToken);
            }

         }

         // Move to next link
         filterChain.doFilter(request, response);
      }
      catch (Exception e) {
         handlerExceptionResolver.resolveException(request, response, null, e);
      }
   }
}
