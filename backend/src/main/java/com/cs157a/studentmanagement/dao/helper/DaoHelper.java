package com.cs157a.studentmanagement.dao.helper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoHelper {

   /**
    * Helper function to easily execute an SQL Queries
    *
    * @param dataSource       The datasource for the current dao
    * @param sql              The SQL statement
    * @param parameterSetter  Sets the parameters of the SQL statement
    * @param resultMapper     Maps the results of the SQL statement to the
    *                         chosen type
    * @return                 T
    * @param <T>              The chosen type of your dao function
    */
   public static <T> T executeQuery(DataSource dataSource,
                              String sql,
                              SQLParameters<PreparedStatement> parameterSetter,
                              SQLFunction<ResultSet, T> resultMapper) {

      // Use try-resource block to automatically close
      try (Connection conn = dataSource.getConnection();
           PreparedStatement pstmt = conn.prepareStatement(sql)) {

         parameterSetter.accept(pstmt);

         try (ResultSet rs = pstmt.executeQuery()) {
            return rs.next() ? resultMapper.apply(rs) : null;
         }
      }
      catch (SQLException e) {
         e.printStackTrace();
         return null;
      }
   }

   /**
    * Executes a JDBC update
    *
    * @param dataSource       The datasource for the current dao
    * @param sql              The SQL statement
    * @param parameterSetter  Sets the parameters of the SQL statement
    * @return                 The number of rows updated
    */
   public static int executeUpdate(DataSource dataSource,
                            String sql,
                            SQLParameters<PreparedStatement> parameterSetter) {
      try (Connection conn = dataSource.getConnection();
           PreparedStatement pstmt = conn.prepareStatement(sql)) {
         parameterSetter.accept(pstmt);
         return pstmt.executeUpdate();
      }
      catch (SQLException e) {
         e.printStackTrace();
         return -1;
      }
   }
}

