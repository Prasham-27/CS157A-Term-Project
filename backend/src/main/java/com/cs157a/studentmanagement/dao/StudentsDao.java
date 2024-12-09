package com.cs157a.studentmanagement.dao;


import com.cs157a.studentmanagement.dao.helper.DaoHelper;
import com.cs157a.studentmanagement.dao.result_objects.StudentCourse;
import com.cs157a.studentmanagement.dao.result_objects.StudentInfo;
import com.cs157a.studentmanagement.utils.enums.AcademicYear;
import com.cs157a.studentmanagement.utils.enums.Days;
import com.cs157a.studentmanagement.utils.enums.EnrollmentStatus;
import com.cs157a.studentmanagement.utils.enums.Grades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * SQL queries for the students table
 */
@Repository
public class StudentsDao {

   private final DataSource dataSource;

   @Autowired
   public StudentsDao(DataSource dataSource) {
      this.dataSource = dataSource;
   }


   public StudentInfo getProfile(Integer studentId) {
      StringBuilder sql = new StringBuilder();
      sql.append("SELECT u.user_id, u.email, u.first_name, u.last_name, ");
      sql.append("s.academic_year, m.major_name FROM users AS u INNER JOIN ");
      sql.append("students AS s ON u.user_id = s.student_id INNER JOIN ");
      sql.append("majors AS m ON s.major_id = m.major_id WHERE u.user_id = ?");

      return DaoHelper.executeQuery(
              dataSource,
              sql.toString(),
              pstmt -> pstmt.setInt(1, studentId),
              rs -> new StudentInfo(
                      rs.getInt("user_id"),
                      rs.getString("major_name"),
                      AcademicYear.valueOf(rs.getString("academic_year")),
                      rs.getString("first_name"),
                      rs.getString("last_name"),
                      rs.getString("email")
              )
      );
   }

   /**
    * Gets all courses that the student has either enrolled in or dropped.
    *
    * @param studentId  The id of the student
    * @return           A list of StudentCourse objects
    */
   public List<StudentCourse> getIncompleteCourses(Integer studentId) {

      // Build the query
      StringBuilder sql = new StringBuilder();
      sql.append("SELECT c.course_num, ci.course_name, ci.points, ");
      sql.append("d.dept_abbreviation, itc.start_time, itc.end_time, ");
      sql.append("e.enrollment_date, e.status, e.grade, u.first_name, u.last_name, ");
      sql.append("string_agg(itcd.day::text, ',' ORDER BY itcd.day ASC) AS days ");
      sql.append("FROM courses AS c ");
      sql.append("INNER JOIN course_info AS ci ON c.course_id = ci.course_id ");
      sql.append("INNER JOIN departments AS d ON c.dept_id = d.dept_id ");
      sql.append("INNER JOIN instructor_to_courses AS itc ON itc.course_id = c.course_id ");
      sql.append("INNER JOIN users AS u ON itc.instructor_id = u.user_id ");
      sql.append("INNER JOIN instructor_to_courses_days AS itcd ON itcd.instructor_course_id = itc.instructor_course_id ");
      sql.append("INNER JOIN enrollments AS e ON e.instructor_course_id = itc.instructor_course_id ");
      sql.append("WHERE e.student_id = ? AND (e.status = 'ENROLLED' OR e.status = 'DROPPED') ");
      sql.append("GROUP BY c.course_num, ci.course_name, ci.points, ");
      sql.append("d.dept_abbreviation, itc.start_time, itc.end_time, ");
      sql.append("e.enrollment_date, e.status, e.grade, u.first_name, u.last_name");

      // Get the query
      return DaoHelper.executeQuery(
              dataSource,
              sql.toString(),
              pstmt -> pstmt.setInt(1, studentId),
              rs -> {
                 List<StudentCourse> result = new ArrayList<StudentCourse>();

                 // Iterate through each row of the result set
                 do {

                    // Convert the days
                    List<Days> daysList =
                            DaoHelper.daysStringToList(rs.getString("days"));

                    // Add course to result list
                    result.add(new StudentCourse(
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("course_name"),
                            rs.getInt("course_num"),
                            rs.getString("dept_abbreviation"),
                            rs.getDouble("points"),
                            rs.getTime("start_time"),
                            rs.getTime("end_time"),
                            daysList,
                            rs.getTimestamp("enrollment_date"),
                            EnrollmentStatus.valueOf((rs.getString("status"))),
                            Grades.getEnum(rs.getString("grade"))
                    ));
                 } while (rs.next());
                 return result;
              }
      );
   }

   /**
    * Gets all courses that the student has COMPLETED
    *
    * @param studentId  The id of the student
    * @return           A list of StudentCourse objects
    */
   public List<StudentCourse> getCompleteCourses(Integer studentId) {

      // Build the query
      StringBuilder sql = new StringBuilder();
      sql.append("SELECT c.course_num, ci.course_name, ci.points, ");
      sql.append("d.dept_abbreviation, ");
      sql.append("e.enrollment_date, e.status, e.grade, u.first_name, u.last_name ");
      sql.append("FROM courses AS c ");
      sql.append("INNER JOIN course_info AS ci ON c.course_id = ci.course_id ");
      sql.append("INNER JOIN departments AS d ON c.dept_id = d.dept_id ");
      sql.append("INNER JOIN instructor_to_courses AS itc ON itc.course_id = c.course_id ");
      sql.append("INNER JOIN users AS u ON itc.instructor_id = u.user_id ");
      sql.append("INNER JOIN enrollments AS e ON e.instructor_course_id = itc.instructor_course_id ");
      sql.append("WHERE e.student_id = ? AND e.status = 'COMPLETED' ");
      sql.append("GROUP BY c.course_num, ci.course_name, ci.points, ");
      sql.append("d.dept_abbreviation, ");
      sql.append("e.enrollment_date, e.status, e.grade, u.first_name, u.last_name;");

      // Get the query
      return DaoHelper.executeQuery(
              dataSource,
              sql.toString(),
              pstmt -> pstmt.setInt(1, studentId),
              rs -> {
                 List<StudentCourse> result = new ArrayList<StudentCourse>();

                 // Iterate through each row of the result set
                 do {

                    // Add course to result list
                    result.add(new StudentCourse(
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("course_name"),
                            rs.getInt("course_num"),
                            rs.getString("dept_abbreviation"),
                            rs.getDouble("points"),
                            null,
                            null,
                            null,
                            rs.getTimestamp("enrollment_date"),
                            EnrollmentStatus.valueOf((rs.getString("status"))),
                            Grades.getEnum(rs.getString("grade"))
                    ));
                 } while (rs.next());
                 return result;
              }
      );
   }

   /**
    * @param studentId The id of the student whose GPA is being calculated
    * @return  The student's GPA
    */
   public Double calculateGpa(Integer studentId) {
      StringBuilder sql = new StringBuilder();
      sql.append("SELECT SUM( CASE e.grade WHEN 'A' THEN 4.0 ");
      sql.append("WHEN 'A-' THEN 3.7 WHEN 'B+' THEN 3.3 ");
      sql.append("WHEN 'B' THEN 3.0 WHEN 'B-' THEN 2.7 ");
      sql.append("WHEN 'C+' THEN 2.3 WHEN 'C' THEN 2.0 ");
      sql.append("WHEN 'C-' THEN 1.7 WHEN 'D' THEN 1.0 WHEN 'F' THEN 0.0 ");
      sql.append("ELSE 0.0 END * ci.points) / NULLIF(SUM(ci.points), 0) AS gpa ");
      sql.append("FROM enrollments e ");
      sql.append("INNER JOIN instructor_to_courses itc ON e.instructor_course_id = itc.instructor_course_id ");
      sql.append("INNER JOIN course_info ci ON itc.course_id = ci.course_id  ");
      sql.append("WHERE e.status = 'COMPLETED' AND e.student_id = ?");

      return DaoHelper.executeQuery(
              dataSource,
              sql.toString(),
              pstmt -> pstmt.setInt(1, studentId),
              rs -> rs.getDouble("gpa")
      );
   }

}
