package com.cs157a.studentmanagement.configuration;

import com.cs157a.studentmanagement.configuration.entrypoint.JwtAuthenticationEntryPoint;
import com.cs157a.studentmanagement.configuration.filters.JwtAuthenticationFilter;
import com.cs157a.studentmanagement.utils.enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityFilterChainConfig {

   private final AuthenticationProvider authenticationProvider;
   private final JwtAuthenticationFilter jwtAuthenticationFilter;
   private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

   public SecurityFilterChainConfig(
           AuthenticationProvider authenticationProvider,
           JwtAuthenticationFilter jwtAuthenticationFilter,
           JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
      this.authenticationProvider = authenticationProvider;
      this.jwtAuthenticationFilter = jwtAuthenticationFilter;
      this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
   }

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http
              .csrf().disable()
              .exceptionHandling()
              .authenticationEntryPoint(jwtAuthenticationEntryPoint)
              .and()
              .cors()
              .and()
              .authorizeRequests()
              .requestMatchers("/api/login", "/api/signup/**", "/api/logout", "/api/check-security").permitAll()
              .requestMatchers("/api/student/**").hasRole(Role.STUDENT.toString())
              .requestMatchers("/api/instructor/**").hasRole(Role.INSTRUCTOR.toString())
              .anyRequest().authenticated()
              .and()
              .sessionManagement()
              .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT
              .and()
              .authenticationProvider(authenticationProvider)
              .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

      return http.build();
   }
}
