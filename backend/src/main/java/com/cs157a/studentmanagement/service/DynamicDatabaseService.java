package com.cs157a.studentmanagement.service;

import com.cs157a.studentmanagement.configuration.DataSourceConfig;
import com.cs157a.studentmanagement.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.cs157a.studentmanagement.repository.UsersRepository;
import com.cs157a.studentmanagement.utils.enums.Role;
import org.springframework.core.env.Environment;

/**
 * A service that logs the user into a specific SQL account based on their role.
 */
@Service
public class DynamicDatabaseService {

   @Autowired
   private DataSourceConfig dataSourceConfig;

   @Autowired
   private RolesRepository rolesRepository;

   @Autowired
   private UsersRepository usersRepository;

   @Autowired
   private Environment environment;

   private JdbcTemplate jdbcTemplate;

   public void setUserDatabase(Long userId) {

      // Get user role
      Role userRole = Role.stringToRole(
              rolesRepository.getRoleName(usersRepository.getRoleId(userId)));

      // Get the user details (role, database username/password) from the session or database
      String dbUsername = getDbUsernameForUser(userRole); // Retrieve based on userId
      String dbPassword = getDbPasswordForUser(userRole); // Retrieve based on userId

      // Set dynamic DataSource
      this.jdbcTemplate = new JdbcTemplate(dataSourceConfig.getDataSource(dbUsername, dbPassword));
   }

   /**
    * Get the specific SQL user based on role
    *
    * @param userRole
    * @return
    */
   public String getDbUsernameForUser(Role userRole) {
      switch (userRole) {
         case STUDENT:
            return environment.getProperty("spring.datasource.student.username");
         case INSTRUCTOR:
            return environment.getProperty("spring.datasource.instructor.username");
         default:
            return environment.getProperty("spring.datasource.login.username");
      }
   }

   /**
    * Get the specific SQL user password on role
    *
    * @param userRole
    * @return
    */
   public String getDbPasswordForUser(Role userRole) {
      switch (userRole) {
         case STUDENT:
            return environment.getProperty("spring.datasource.student.password");
         case INSTRUCTOR:
            return environment.getProperty("spring.datasource.instructor.password");
         default:
            return environment.getProperty("spring.datasource.login.password");
      }
   }

   public void executeQuery(String query) {
      jdbcTemplate.execute(query);
   }
}
