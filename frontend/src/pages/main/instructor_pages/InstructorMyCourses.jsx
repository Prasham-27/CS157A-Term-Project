
import React, { useState, useEffect } from "react";
import { 
    Flex, 
    Text, 
    Divider, 
    Grid,
    GridItem,
    HStack,
    VStack,
    Button,
    Table, 
    Thead, 
    Tbody,
    Tr,
    Th, 
    Td 
    } from "@chakra-ui/react";
import { Link, useNavigate } from "react-router-dom";
import { EnrollmentStatus } from "../../../enums/enums.js"
import styles from "./InstructorMyCourses.module.css"
import axiosInstance from "../../../axiosInstance";

export default function InstructorMyCourses() {

    const [ inactiveCourses, setInactiveCourses ] = useState([]);
    const [ activeCourses, setActiveCourses ] = useState([]);

    const getMyCourses = async () => {
        try {
            let response = await axiosInstance.get("/api/instructor/mycourses");
            console.log(response);
            if (response.status === 200) {  
                setInactiveCourses(response.data.inactive_courses || []);
                setActiveCourses(response.data.active_courses || []);
            }
        } catch (err) {
            console.error(err);
        }

    }

    useEffect(() => {
        getMyCourses();
    }, []);

    return (
        <Flex className={styles.container}>
        <Text className={styles.statusText} color="#0055A2">
            Active Courses
        </Text>
        {activeCourses && activeCourses.length === 0 ? (
          <Text className={styles.messageText}>No active courses</Text>
        ) : (
            activeCourses.map((instructorCourse) => (
            <DepartmentCourseComponent key={instructorCourse.instructor_course_id} course={instructorCourse} />
          ))
        )}  
        <br />
        <Text className={styles.statusText} color="#0055A2">
            Inactive Courses
        </Text>
        {inactiveCourses && inactiveCourses.length === 0 ? (
          <Text className={styles.messageText}>No active courses</Text>
        ) : (
            inactiveCourses.map((instructorCourse) => (
            <DepartmentCourseComponent key={instructorCourse.instructor_course_id} course={instructorCourse} />
          ))
        )}
      </Flex>
    );
}

function DepartmentCourseComponent({ course }) {

    const navigate = useNavigate(); 

    const handleStudentClick = () => {
      navigate(`${course.instructor_course_id}/students`, { state: { course } });
    };

    return (
        <>
        <Text className={styles.heading}>
            {course.dept_abbreviation} {course.course_number}: {course.course_name}
        </Text>
        <Table size="sm" textAlign="center">
            <Thead>
            <Tr>
                <Th textAlign="center">Time</Th>
                <Th textAlign="center">Days</Th>
                <Th textAlign="center">Credit Hours</Th>
                <Th textAlign="center">Enrollment</Th>
                <Th textAlign="center">View Students</Th>
            </Tr>
            </Thead>
            <Tbody>
            <Tr>
                <Td textAlign="center">{course.start_time} - {course.end_time}</Td>
                <Td textAlign="center">{course.course_days.join(',')}</Td>
                <Td textAlign="center">{course.points}</Td>
                <Td textAlign="center">{course.num_enrolled} / {course.max_enrollment}</Td>
                <Td textAlign="center">
                    <Button onClick={handleStudentClick} >
                            view
                    </Button>
                </Td>
            </Tr>
            </Tbody>
        </Table>
        </>
    );
  }