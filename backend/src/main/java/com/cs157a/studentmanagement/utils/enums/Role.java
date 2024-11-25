package com.cs157a.studentmanagement.utils.enums;

import com.cs157a.studentmanagement.repository.RolesRepository;
import com.cs157a.studentmanagement.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Defines the Roles for the Users table
 */
public enum Role {
   STUDENT, INSTRUCTOR;

   public static Role stringToRole(String s) {
      for (Role role : Role.values())
            if (s.equals(role.toString()))
               return role;
      return null;
   }
}