package com.cs157a.studentmanagement.dao;

import com.cs157a.studentmanagement.dao.helper.DaoHelper;
import com.cs157a.studentmanagement.dao.result_objects.Course;
import com.cs157a.studentmanagement.dao.result_objects.Department;
import com.cs157a.studentmanagement.dao.result_objects.InstructorCourseInfo;
import com.cs157a.studentmanagement.utils.enums.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentAndCoursesDao {

   private final DataSource dataSource;

   @Autowired
   public DepartmentAndCoursesDao(DataSource dataSource) {
      this.dataSource = dataSource;
   }

   public List<Department> findAllDepartments() {
      String sql = "SELECT * FROM departments";

      return DaoHelper.executeQuery(
              dataSource,
              sql,
              pstmt -> {},
              rs -> {
                 List<Department> depts = new ArrayList<>();

                 do {
                    depts.add(new Department(
                            rs.getInt("dept_id"),
                            rs.getString("dept_name"),
                            rs.getString("dept_abbreviation")
                    ));
                 } while (rs.next());
                 return depts;
              }
      );
   }

   /**
    * @param deptId  The department_id of the courses we want
    * @return        The list of courses from the department
    */
   public List<Course> findAllCoursesFromDepartment(Integer deptId) {
      String sql = "SELECT c.course_id, c.course_num, ci.course_name, ci.points "
              + "FROM courses AS c LEFT OUTER JOIN course_info AS ci ON "
              + "c.course_id = ci.course_id WHERE c.dept_id = ?";

      // Run the query
      return DaoHelper.executeQuery(
              dataSource,
              sql,
              pstmt -> pstmt.setInt(1, deptId),
              rs -> {
                 List<Course> courses = new ArrayList<>();
                 do {
                    courses.add(new Course(
                            rs.getInt("course_id"),
                            rs.getInt("course_num"),
                            rs.getString("course_name"),
                            rs.getDouble("points")
                    ));
                 } while (rs.next());
                 return courses;
              }
      );
   }

   /**
    * @param courseId The id of the course
    * @return         The departmentId of the course
    */
   public Integer getCourseDeptId(Integer courseId) {
      String sql = "SELECT dept_id FROM courses WHERE course_id = ?";
      return DaoHelper.executeQuery(
              dataSource,
              sql,
              pstmt -> pstmt.setInt(1, courseId),
              rs -> rs.getInt("dept_id")
      );
   }

   public List<InstructorCourseInfo> findInstructorsCourseInfo(Integer courseId) {
      StringBuilder sql = new StringBuilder();
      sql.append("SELECT itc.instructor_course_id, itc.max_enrollment, itc.num_enrolled, itc.start_time, itc.end_time, ");
      sql.append(" string_agg(itcd.day::text, ',' ORDER BY itcd.day ASC) AS days, ");
      sql.append("u.first_name, u.last_name FROM instructor_to_courses AS itc INNER JOIN ");
      sql.append("instructor_to_courses_days AS itcd ON itc.instructor_course_id = itcd.instructor_course_id ");
      sql.append("INNER JOIN users AS u ON itc.instructor_id = u.user_id ");
      sql.append("WHERE itc.course_id = ? ");
      sql.append("GROUP BY itc.instructor_course_id, itc.max_enrollment, itc.num_enrolled, itc.start_time, itc.end_time, u.first_name, u.last_name");

      return DaoHelper.executeQuery(
              dataSource,
              sql.toString(),
              pstmt -> pstmt.setInt(1, courseId),
              rs -> {
                 List<InstructorCourseInfo> result = new ArrayList<>();
                 do {
                    List<Days> daysList =
                            DaoHelper.daysStringToList(rs.getString("days"));

                    result.add(new InstructorCourseInfo(
                            rs.getInt("instructor_course_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getInt("max_enrollment"),
                            rs.getInt("num_enrolled"),
                            rs.getTime("start_time"),
                            rs.getTime("end_time"),
                            daysList
                    ));
                 } while (rs.next());
                 return result;
              }
      );
   }
}
