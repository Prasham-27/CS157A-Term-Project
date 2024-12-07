package com.cs157a.studentmanagement.dao;

import com.cs157a.studentmanagement.dao.helper.DaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;

/**
 * The SQL queries associated with the Users table
 */
@Repository
public class UsersDao {

   private final DataSource dataSource;

   @Autowired
   public UsersDao(DataSource dataSource) {
      this.dataSource = dataSource;
   }

   /**
    * Gets the password hash associated with the user in the database
    *
    * @param userId The userId for the password hash
    * @return       The password hash in bcrypt or null
    */
   public String getPasswordHash(Long userId) {
      String sql = "SELECT password FROM Users WHERE user_id = ?";
      return DaoHelper.executeQuery(
              dataSource,
              sql,
              pstmt -> pstmt.setLong(1, userId),
              rs -> rs.getString("password")
      );
   }

   /**
    * Gets the name of the role associated with the user
    *
    * @param userId  The userId of who you want to find the roleId of
    * @return        The rolenName of null
    */
   public String getRoleName(Long userId) {
      String sql = "SELECT role_name FROM roles WHERE role_id = " +
              "(SELECT role_id FROM users WHERE user_id = ?)";
      return DaoHelper.executeQuery(
              dataSource,
              sql,
              pstmt -> pstmt.setLong(1, userId),
              rs -> rs.getString("role_name")
      );
   }

   /**
    * Inserts a new user into the database
    *
    * @param email            The email of the new user
    * @param hashedPassword   The hashed password of the new user
    * @param firstName        The first name of the new user
    * @param lastName         The last name of the new user
    * @param role             The roleId of the new user
    * @return                 The number of rows inserted, -1 failure
    */
   public Integer insertUser(
           String email,
           String hashedPassword,
           String firstName,
           String lastName,
           Integer role) {

      // Insert user values
      String sql = "INSERT INTO users (email, password, first_name, last_name, role_id) " +
      "VALUES (?, ?, ?, ?, ?)";
      return DaoHelper.executeUpdate(
              dataSource,
              sql,
              pstmt -> {
                 pstmt.setString(1, email);
                 pstmt.setString(2, hashedPassword);
                 pstmt.setString(3, firstName);
                 pstmt.setString(4, lastName);
                 pstmt.setInt(5, role);
              }
      );
   }

   /**
    * @return A spring security object for login
    */
   public User getUserObject(Long userId) {
      String sql = "SELECT user_id, password FROM users WHERE user_id = ?";
      String role = "ROLE_" + getRoleName(userId);

      return DaoHelper.executeQuery(
              dataSource,
              sql,
              pstmt -> pstmt.setLong(1, userId),
              rs -> new User(
                      Long.toString(rs.getLong("user_id")),
                      rs.getString("password"),
                      Collections.singletonList(new SimpleGrantedAuthority(role))
              )
      );
   }

}
