import './App.css';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { ChakraProvider } from "@chakra-ui/react";
import WelcomePage from './pages/root/WelcomePage';
import StudentLogin from './pages/login/StudentLogin';
import FacultyLogin from './pages/login/FacultyLogin';

import Student from './pages/main/Student';
import StudentCourses from './pages/main/student_pages/StudentCourses';
import StudentMyCourses from './pages/main/student_pages/StudentMyCourses';
import StudentGrades from './pages/main/student_pages/StudentGrades';
import StudentProfile from './pages/main/student_pages/StudentProfile';
import StudentDepartmentCourses from './pages/main/student_pages/DepartmentCourses';
import StudentCourseInfo from './pages/main/student_pages/CourseInfo';

import Instructor from './pages/main/Instructor';
import InstructorMyCourses from './pages/main/instructor_pages/InstructorMyCourses';
import InstructorProfile from './pages/main/instructor_pages/InstructorProfile';

function App() {
  return (
    <ChakraProvider>
      <BrowserRouter>
      <Routes>
          <Route path="/" element={<WelcomePage />} />
          <Route path="/studentlogin" element={<StudentLogin />} />
          <Route path="/facultylogin" element={<FacultyLogin />} />

          {/* Student routes */}
          <Route path="/student" element={<Student />}>
            <Route path="mycourses" element={<StudentMyCourses />} />
            <Route path="courses" element={<StudentCourses />} />
            <Route path="grades" element={<StudentGrades />} />
            <Route path="profile" element={<StudentProfile />} />
            <Route path="dept-courses/:id" element={<StudentDepartmentCourses />} />
            <Route path="course-info/:id" element={<StudentCourseInfo />} />
          </Route>

          {/* Instructor routes */}
          <Route path="/instructor" element={<Instructor />}>
            <Route path="inventory" element={<InstructorMyCourses />} />
            <Route path="profile" element={<InstructorProfile />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </ChakraProvider>
  );
}

export default App;
