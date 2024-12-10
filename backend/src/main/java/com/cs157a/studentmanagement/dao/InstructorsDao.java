package com.cs157a.studentmanagement.dao;

import com.cs157a.studentmanagement.dao.helper.DaoHelper;
import com.cs157a.studentmanagement.dao.result_objects.Department;
import com.cs157a.studentmanagement.dao.result_objects.instructor.InstructorCourse;
import com.cs157a.studentmanagement.dao.result_objects.instructor.StudentView;
import com.cs157a.studentmanagement.model.InstructorToCourses;
import com.cs157a.studentmanagement.utils.enums.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InstructorsDao {

   private final DataSource dataSource;

   @Autowired
   public InstructorsDao(DataSource dataSource) {
      this.dataSource = dataSource;
   }


   /**
    * @param instructorId The id of the instructor whose courses we are looking for
    * @return             A list of instructor course objects
    */
   public List<InstructorCourse> findAllActiveCourses(Integer instructorId) {

      StringBuilder sql = new StringBuilder();
      sql.append("SELECT c.course_num, ci.course_name, ci.points, ");
      sql.append("d.dept_abbreviation, itc.instructor_course_id, itc.start_time, itc.end_time, ");
      sql.append("itc.max_enrollment, itc.num_enrolled, ");
      sql.append("string_agg(itcd.day::text, ',' ORDER BY itcd.day ASC) AS days ");
      sql.append("FROM courses AS c ");
      sql.append("INNER JOIN course_info AS ci ON c.course_id = ci.course_id ");
      sql.append("INNER JOIN departments AS d ON c.dept_id = d.dept_id ");
      sql.append("INNER JOIN instructor_to_courses AS itc ON itc.course_id = c.course_id ");
      sql.append("INNER JOIN instructor_to_courses_days AS itcd ON itcd.instructor_course_id = itc.instructor_course_id ");
      sql.append("WHERE itc.instructor_id = ? AND itc.is_finished = FALSE ");
      sql.append("GROUP BY c.course_num, ci.course_name, ci.points, ");
      sql.append("d.dept_abbreviation, itc.start_time, itc.end_time, itc.instructor_course_id, itc.max_enrollment, itc.num_enrolled");

      return DaoHelper.executeQuery(
              dataSource,
              sql.toString(),
              pstmt -> pstmt.setInt(1, instructorId),
              rs -> {
                 List<InstructorCourse> result = new ArrayList<>();
                 do {
                    result.add(new InstructorCourse(
                            rs.getInt("instructor_course_id"),
                            rs.getString("course_name"),
                            rs.getInt("course_num"),
                            rs.getString("dept_abbreviation"),
                            rs.getDouble("points"),
                            rs.getTime("start_time"),
                            rs.getTime("end_time"),
                            DaoHelper.daysStringToList(rs.getString("days")),
                            rs.getInt("max_enrollment"),
                            rs.getInt("num_enrolled")
                    ));
                 } while (rs.next());

                 return result;
              }
      );
   }

   /**
    * Allows a search for students in a particular course by first and last name
    *
    * @param instructorCourseId The id of the course being searched for students
    */
   public List<StudentView> findStudentsInCourse(Integer instructorCourseId, String searchTerm) {
      StringBuilder sql = new StringBuilder();
      sql.append("SELECT e.student_id, u.first_name, u.last_name, u.email, m.major_name ");
      sql.append("FROM enrollments AS e INNER JOIN users AS u ON e.student_id = u.user_id ");
      sql.append("INNER JOIN students AS s ON e.student_id = s.student_id ");
      sql.append("INNER JOIN majors AS m ON m.major_id = s.major_id WHERE e.instructor_course_id = ? ");
      sql.append("AND (? IS NULL OR (u.first_name ILIKE ? OR u.last_name ILIKE ?))");

      return DaoHelper.executeQuery(
              dataSource,
              sql.toString(),
              pstmt -> {
                 pstmt.setInt(1, instructorCourseId);
                 pstmt.setString(2, searchTerm); // NULL check
                 pstmt.setString(3, '%' + searchTerm + '%'); // % Scan around the entire string
                 pstmt.setString(4, '%' + searchTerm + '%');

                 System.out.println(pstmt.toString());
              },
              rs -> {
                 List<StudentView> result = new ArrayList<>();
                 do {
                    result.add(new StudentView(
                            rs.getInt("student_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("email"),
                            rs.getString("major_name")
                    ));
                 } while (rs.next());

                 return result;
              }
      );
   }

   /**
    * @param instructorId The id of the instructor whose courses we are looking for
    * @return             A list of instructor course objects
    */
   public List<InstructorCourse> findAllPastCourses(Integer instructorId) {

      StringBuilder sql = new StringBuilder();
      sql.append("SELECT c.course_num, ci.course_name, ci.points, ");
      sql.append("d.dept_abbreviation, itc.instructor_course_id, itc.start_time, itc.end_time, ");
      sql.append("itc.max_enrollment, itc.num_enrolled, ");
      sql.append("string_agg(itcd.day::text, ',' ORDER BY itcd.day ASC) AS days ");
      sql.append("FROM courses AS c ");
      sql.append("INNER JOIN course_info AS ci ON c.course_id = ci.course_id ");
      sql.append("INNER JOIN departments AS d ON c.dept_id = d.dept_id ");
      sql.append("INNER JOIN instructor_to_courses AS itc ON itc.course_id = c.course_id ");
      sql.append("INNER JOIN instructor_to_courses_days AS itcd ON itcd.instructor_course_id = itc.instructor_course_id ");
      sql.append("WHERE itc.instructor_id = ? AND itc.is_finished = TRUE ");
      sql.append("GROUP BY c.course_num, ci.course_name, ci.points, ");
      sql.append("d.dept_abbreviation, itc.start_time, itc.end_time, itc.instructor_course_id, itc.max_enrollment, itc.num_enrolled");

      return DaoHelper.executeQuery(
              dataSource,
              sql.toString(),
              pstmt -> pstmt.setInt(1, instructorId),
              rs -> {
                 List<InstructorCourse> result = new ArrayList<>();
                 do {
                    result.add(new InstructorCourse(
                            rs.getInt("instructor_course_id"),
                            rs.getString("course_name"),
                            rs.getInt("course_num"),
                            rs.getString("dept_abbreviation"),
                            rs.getDouble("points"),
                            rs.getTime("start_time"),
                            rs.getTime("end_time"),
                            DaoHelper.daysStringToList(rs.getString("days")),
                            rs.getInt("max_enrollment"),
                            rs.getInt("num_enrolled")
                    ));
                 } while (rs.next());

                 return result;
              }
      );
   }

   // Instructors able to search for students


   /**
    * @param instructorId The id of the instructor whose department we are finding
    * @return             A Department object of where the instructor is from
    */
   public Department getInstructorDept(Integer instructorId) {
      String sql = "SELECT * FROM departments WHERE dept_id = " +
              "(SELECT dept_id FROM instructors WHERE instructor_id = ?)";
      return DaoHelper.executeQuery(
              dataSource,
              sql,
              pstmt -> pstmt.setInt(1, instructorId),
              rs -> {
                 return new Department(
                         rs.getInt("dept_id"),
                         rs.getString("dept_name"),
                         rs.getString("dept_abbreviation")
                 );
              }
      );
   }


   /**
    * @param instructorCourse An instructor course that has had all of its
    *                         values populated
    * @return                 If the course was successfully added
    */
   public boolean createCourse(InstructorToCourses instructorCourse) {
      StringBuilder sql = new StringBuilder();
      sql.append("INSERT course_into_courses (instructor_id, course_id, ");
      sql.append("max_enrollment, num_enrolled, start_time, end_time) ");
      sql.append("VALUES (?, ?, ?, ?, ?, ?)");

      int rowsInsert = DaoHelper.executeUpdate(
              dataSource,
              sql.toString(),
              pstmt -> {
                 pstmt.setInt(1, instructorCourse.getInstructorId());
                 pstmt.setInt(2, instructorCourse.getCourseId());
                 pstmt.setInt(3, instructorCourse.getMaxEnrollment());
                 pstmt.setInt(4, instructorCourse.getNumEnrolled());
                 pstmt.setTime(5, instructorCourse.getStartTime());
                 pstmt.setTime(6, instructorCourse.getEndTime());
              }
      );

      if (rowsInsert >= 0)
         return false;

      // Batch update the days
      String sqlBatch = "INSERT INTO instructor_to_courses_days VALUES " +
              "((SELECT instructor_course_id FROM instructor_to_courses " +
              "WHERE instructor_id = ? AND course_id = ?), ?::days)";

      // Try-with-resources
      try (Connection conn = dataSource.getConnection();
           PreparedStatement pstmt = conn.prepareStatement(sqlBatch)) {
         for (Days day : instructorCourse.getDays()) {
            pstmt.setInt(1, instructorCourse.getInstructorId());
            pstmt.setInt(2, instructorCourse.getCourseId());
            pstmt.setString(3, day.toString());
            pstmt.addBatch();
         }
         pstmt.executeBatch();
      }
      catch (SQLException e) {
         e.printStackTrace();
      }
      return true;
   }


   /**
    * @param students            List of objects with only the studentIds and names
    * @param instructorCourseId  The course being completed
    * @return                    True if completion successful
    */
   public boolean completeCourse(List<StudentView> students, Integer instructorCourseId) {

      // Set course as finished
      String sqlCourse =
              "UPDATE instructor_to_courses SET is_finished = TRUE WHERE instructor_course_id = ?";
      int numCourseUpdated = DaoHelper.executeUpdate(
              dataSource,
              sqlCourse,
              pstmt -> pstmt.setInt(1, instructorCourseId)
      );
      // If zero updates, it was already completed
      if (numCourseUpdated <= 0)
         return false;

      // Batch ipdate the course of each student
      String sql = "UPDATE enrollments SET grade = ? WHERE student_id = ? AND instructor_course_id = ?";

      // Try-with-resources
      try (Connection conn = dataSource.getConnection();
           PreparedStatement pstmt = conn.prepareStatement(sql)) {
         for (StudentView student : students) {
            pstmt.setString(1, student.getGrade().toString());
            pstmt.setInt(2, student.getStudentId());
            pstmt.setInt(3, instructorCourseId);
            pstmt.addBatch();
         }
         pstmt.executeBatch();
      }
      catch (SQLException e) {
         e.printStackTrace();
      }
      return true;
   }
}
