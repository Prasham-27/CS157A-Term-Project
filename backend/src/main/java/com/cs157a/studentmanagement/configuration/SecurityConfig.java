package com.cs157a.studentmanagement.configuration;

import com.cs157a.studentmanagement.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity(debug=true)
public class SecurityConfig {

   UserDetailsServiceImpl userDetailsService;

   public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
      this.userDetailsService = userDetailsService;
   }

   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }

   @Bean
   public AuthenticationManager authenticationManager(
           AuthenticationConfiguration configuration
   ) throws Exception {
      return configuration.getAuthenticationManager();
   }

   /**
    * Sets the strategy of how to perform authentication
    *
    * @param userDetailsService
    * @param passwordEncoder
    * @return
    */
   @Bean
   public AuthenticationProvider authenticationProvider(
           UserDetailsService userDetailsService,
           PasswordEncoder passwordEncoder
   ){
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
      authProvider.setUserDetailsService(userDetailsService);
      authProvider.setPasswordEncoder(passwordEncoder);
      return authProvider;
   }

}