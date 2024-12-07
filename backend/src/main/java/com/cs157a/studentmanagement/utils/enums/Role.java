package com.cs157a.studentmanagement.utils.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * Defines the Roles for the Users table
 */
public enum Role implements GrantedAuthority {
   STUDENT, INSTRUCTOR;

   @Override
   public String getAuthority() {
      return this.name();
   }
}