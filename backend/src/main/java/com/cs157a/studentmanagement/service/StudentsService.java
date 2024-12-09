package com.cs157a.studentmanagement.service;

import com.cs157a.studentmanagement.dao.StudentsDao;
import com.cs157a.studentmanagement.dao.result_objects.StudentCourse;
import com.cs157a.studentmanagement.dao.result_objects.StudentInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsService {

   private final StudentsDao studentsDao;

   public StudentsService(StudentsDao studentsDao) {
      this.studentsDao = studentsDao;
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

   public Double getGpa(Integer studentId) {
      return studentsDao.calculateGpa(studentId);
   }
}
