package com.cs157a.studentmanagement.service;

import com.cs157a.studentmanagement.dao.DepartmentAndCoursesDao;
import com.cs157a.studentmanagement.dao.result_objects.student.Course;
import com.cs157a.studentmanagement.dao.result_objects.Department;
import com.cs157a.studentmanagement.dao.result_objects.student.InstructorCourseInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentAndCoursesService {

   private final DepartmentAndCoursesDao departmentAndCoursesDao;

   public DepartmentAndCoursesService(DepartmentAndCoursesDao departmentAndCoursesDao) {
      this.departmentAndCoursesDao = departmentAndCoursesDao;
   }

   public List<Department> getAllDepartments() {
      return departmentAndCoursesDao.findAllDepartments();
   }

   public List<Course> getAllCoursesFromDepartment(Integer deptId) {
      return departmentAndCoursesDao.findAllCoursesFromDepartment(deptId);
   }

   public Integer getCourseDeptId(Integer courseId) {
      return departmentAndCoursesDao.getCourseDeptId(courseId);
   }

   public List<InstructorCourseInfo> getInstructorCourseInfo(Integer courseId) {
      return departmentAndCoursesDao.findInstructorsCourseInfo(courseId);
   }
}
