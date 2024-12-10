package com.cs157a.studentmanagement.service;

import com.cs157a.studentmanagement.dao.DepartmentAndCoursesDao;
import com.cs157a.studentmanagement.dao.StudentsDao;
import com.cs157a.studentmanagement.dao.result_objects.student.StudentCourse;
import com.cs157a.studentmanagement.dao.result_objects.student.StudentInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentsService {

   private final StudentsDao studentsDao;
   private final DepartmentAndCoursesDao departmentAndCoursesDao;

   public StudentsService(StudentsDao studentsDao, DepartmentAndCoursesDao departmentAndCoursesDao) {
      this.studentsDao = studentsDao;
      this.departmentAndCoursesDao = departmentAndCoursesDao;
   }

   public StudentInfo getProfile(Integer studentId) {
      return studentsDao.getProfile(studentId);
   }

   /**
    * Gets all student courses with EnrollmentStatus ENROLLED or DROPPED
    *
    * @param studentId
    * @return
    */
   public List<StudentCourse> getIncompleteCourses(Integer studentId) {
      return studentsDao.getIncompleteCourses(studentId);
   }

   /**
    * Gets all student courses with EnrollmentStatus COMPLETED
    *
    * @param studentId
    * @return
    */
   public List<StudentCourse> getCompleteCourses(Integer studentId) {
      return studentsDao.getCompleteCourses(studentId);
   }

   /**
    * @return The GPA of the student
    */
   public Double getGpa(Integer studentId) {
      return studentsDao.calculateGpa(studentId);
   }

   /**
    * @return True if a student has already enrolled in or completed a course
    *         with the same course_id as the instructor_course_id.
    */
   public boolean checkEnrolledOrCompleted(Integer studentId, Integer instructorCourseId) {
      return studentsDao.checkCourseCompleteOrEnrolled(instructorCourseId, studentId).booleanValue();
   }

   /**
    * @return True if a student has already dropped in or completed a course
    *         with the same course_id as the instructor_course_id.
    */
   public boolean checkCompleted(Integer studentId, Integer instructorCourseId) {
      return studentsDao.checkCourseComplete(instructorCourseId, studentId).booleanValue();
   }

   /**
    * Perform a transaction to attempt to enroll a student into a course
    *
    * @return true is enrollment was successful
    */
   @Transactional
   public boolean enrollStudentIntoCourse(Integer studentId, Integer instructorCourseId) {

      // Check if the course is full
      if (departmentAndCoursesDao.checkCourseIsFull(instructorCourseId))
         return false;

      // Attempt to enroll the student into the course
      return studentsDao.enrollStudent(instructorCourseId, studentId).intValue() > 0;
   }

   /**
    * Drop student from course, assuming you have already checked if
    * have not already dropped or completed the course.
    *
    * @return If the student was successfully dropped
    */
   public boolean dropStudentFromCourse(Integer studentId, Integer instructorCourseId) {
      return studentsDao.dropStudent(instructorCourseId, studentId).intValue() > 0;
   }
}
