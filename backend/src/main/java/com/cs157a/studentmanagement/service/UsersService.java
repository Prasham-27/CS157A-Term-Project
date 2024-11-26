package com.cs157a.studentmanagement.service;

import com.cs157a.studentmanagement.repository.RolesRepository;
import com.cs157a.studentmanagement.repository.UsersRepository;
import com.cs157a.studentmanagement.utils.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsersService {

   @Autowired
   private UsersRepository usersRepository;

   @Autowired
   private RolesRepository rolesRepository;

   @Autowired
   private PasswordEncoder passwordEncoder;


   /**
    * Checks if the userId's role is the same as the role passed in
    *
    * @param userId
    * @param role
    * @return
    */
   public boolean roleIsEqual(Long userId, Role role) {
      return rolesRepository.getRoleName(usersRepository.getRoleId(userId))
              .equals(role.toString());
   }

   /**
    * A helper functions to check if the password matches with the one in the
    * Database.
    *
    * @param password    The unhashed user password
    * @param userId      The userId associated with the password
    * @return            Whether the two passwords hashes match
    */
   public boolean checkPassword(String password, Long userId) {
      String storedHash = usersRepository.getPasswordHash(userId);
      return passwordEncoder.matches(password, storedHash);
   }

   public boolean signUp(String email, String password, String firstName,
                         String lastName, Integer role) {
      String hashedPassword = passwordEncoder.encode(password);
      int rowsInserted = usersRepository.insertUser(email, hashedPassword,
              firstName, lastName, role);

      return (rowsInserted > 0);
   }


}