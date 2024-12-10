package com.cs157a.studentmanagement.service;

import com.cs157a.studentmanagement.dao.InstructorsDao;
import com.cs157a.studentmanagement.dao.result_objects.instructor.InstructorCourse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorsService {

   private final InstructorsDao instructorsDao;

   public InstructorsService(InstructorsDao instructorsDao) {
      this.instructorsDao = instructorsDao;
   }

   public List<InstructorCourse> getAllActiveCourses(Integer instructorId) {
      return instructorsDao.findAllActiveCourses(instructorId);
   }


}
