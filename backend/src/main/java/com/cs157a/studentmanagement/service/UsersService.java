/**
 * Contains the transactions for the users table, such as signup, login, etc.
 */


package com.cs157a.studentmanagement.service;

import com.cs157a.studentmanagement.dao.RolesDao;
import com.cs157a.studentmanagement.dao.UsersDao;
import com.cs157a.studentmanagement.utils.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provides services related to the users table that the API uses.
 */
@Service
public class UsersService {

   private final UsersDao usersDao;

   private final RolesDao rolesDao;

   @Autowired
   private PasswordEncoder passwordEncoder;


   public UsersService(UsersDao userDao, RolesDao rolesDao) {
      this.usersDao = userDao;
      this.rolesDao = rolesDao;
   }

   /**
    * Gets the roleName of the role associated with the user
    *
    * @param userId  The userId of the associated user
    * @return        The name of the user's role or null
    */
   public String getRoleName(Long userId) {
      return usersDao.getRoleName(userId);
   }

   /**
    * A helper function to check if the password matches with the one in the
    * Database. This function is used in the LoginController.
    *
    * @param password    The unhashed user password
    * @param userId      The userId associated with the password
    * @return            Whether the two passwords hashes match
    */
   public boolean checkPassword(String password, Long userId) {
      String storedHash = usersDao.getPasswordHash(userId);
      return passwordEncoder.matches(password, storedHash);
   }

   /**
    * Signs up a user by adding them to the roles table and hashing their
    * password with bcrypt.
    *
    * @param email      The email of the user
    * @param password   The unhashed password of the user
    * @param firstName  The first name of the user
    * @param lastName   The last name of the user
    * @param role       The role associated with the user
    * @return           If the role was successfully inserted
    */
   @Transactional
   public boolean signUp(String email, String password, String firstName,
                         String lastName, Role role) {
      String hashedPassword = passwordEncoder.encode(password);
      Integer roleId = rolesDao.getRoleId(role.toString());
      int rowsInserted = usersDao.insertUser(email, hashedPassword,
              firstName, lastName, roleId);

      return (rowsInserted > 0);
   }

}