package com.cs157a.studentmanagement.service;

import com.cs157a.studentmanagement.dao.DepartmentAndCoursesDao;
import com.cs157a.studentmanagement.dao.InstructorsDao;
import com.cs157a.studentmanagement.dao.result_objects.Department;
import com.cs157a.studentmanagement.dao.result_objects.instructor.InstructorCourse;
import com.cs157a.studentmanagement.dao.result_objects.instructor.StudentView;
import com.cs157a.studentmanagement.dao.result_objects.student.Course;
import com.cs157a.studentmanagement.model.InstructorToCourses;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorsService {

   private final InstructorsDao instructorsDao;
   private final DepartmentAndCoursesDao departmentAndCoursesDao;

   public InstructorsService(InstructorsDao instructorsDao, DepartmentAndCoursesDao departmentAndCoursesDao) {
      this.instructorsDao = instructorsDao;
      this.departmentAndCoursesDao = departmentAndCoursesDao;
   }

   /**
    * @return The instructor's active courses
    */
   public List<InstructorCourse> getAllActiveCourses(Integer instructorId) {
      return instructorsDao.findAllActiveCourses(instructorId);
   }

   /**
    * @return All of the instructor's past courses
    */
   public List<InstructorCourse> getAllInactiveCourses(Integer instructorId) {
      return instructorsDao.findAllPastCourses(instructorId);
   }

   /**
    * @return All of students in the instructor's course
    */
   public List<StudentView> getAllStudentsInCourse(Integer instructorCourseId, String searchTerm) {
      return instructorsDao.findStudentsInCourse(instructorCourseId, searchTerm);
   }

   /**
    * @return Get all courses in the instructor department
    */
   public List<Course> getAllCoursesInInstructorDepartment(Integer instructorId) {
      Department dept = instructorsDao.getInstructorDept(instructorId);
      return departmentAndCoursesDao.findAllCoursesFromDepartment(dept.getDeptId());
   }

   /**
    * @param instructorsToCourses An object that holds data to put into the i
    *                             instructor to courses table.
    * @return                     If the insertion was successful
    */
   public boolean createInstructorCourse(InstructorToCourses instructorsToCourses) {
      return instructorsDao.createCourse(instructorsToCourses);
   }

   /**
    * @param students           A list of student_ids and grades
    * @param instructorCourseId The instructor course being completed
    * @return                   true if update successful
    */
   public boolean completeCourse(List<StudentView> students, Integer instructorCourseId) {
      return instructorsDao.completeCourse(students, instructorCourseId);
   }

}
