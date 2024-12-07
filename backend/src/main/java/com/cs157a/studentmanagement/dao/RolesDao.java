package com.cs157a.studentmanagement.dao;

import com.cs157a.studentmanagement.dao.helper.DaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * The SQL queries associated with the Roles tables
 */
@Repository
public class RolesDao {

   private final DataSource dataSource;

   @Autowired
   public RolesDao(DataSource dataSource) {
      this.dataSource = dataSource;
   }

   /**
    * Gets the role name of the associated roleId
    *
    * @param roleId The roleId for the associated role name
    * @return       The role name or null if does not exist
    */
   public String getRoleName(Integer roleId) {
      String sql = "SELECT role_name FROM roles WHERE role_id = ?";
      return DaoHelper.executeQuery(
              dataSource,
              sql,
              pstmt -> pstmt.setInt(1, roleId),
              rs -> rs.getString("role_name")
      );
   }

   /**
    * Get the role_id of the associated role name.
    *
    * @param roleName The role name of the role id
    * @return         The id of the role, or null if does not exist
    */
   public Integer getRoleId(String roleName) {
      String sql = "SELECT role_id FROM roles WHERE role_name = ?";
      return DaoHelper.executeQuery(
              dataSource,
              sql,
              pstmt -> pstmt.setString(1, roleName),
              rs -> rs.getInt("role_id")
      );
   }
}
