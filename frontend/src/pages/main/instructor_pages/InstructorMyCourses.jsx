
import React, { useState, useEffect } from "react";
import { 
    Flex, 
    Text, 
    Divider, 
    Grid,
    GridItem,
    HStack,
    VStack,
    Button
    } from "@chakra-ui/react";
import { Link } from "react-router-dom";
import { EnrollmentStatus } from "../../../enums/enums.js"
import styles from "./InstructorMyCourses.module.css"
import axiosInstance from "../../../axiosInstance";

export default function InstructorMyCourses() {

    const getMyCourses = async () => {
        

    }

    useEffect(() => {
        getCourses();
    }, []);

    return (
        <Flex className={styles.container}>
        <Text className={styles.statusText} color="#0055A2">
        {dept && course ? <Text>{dept.dept_abbreviation} {course.course_num} {course.course_name}</Text> : "No department selected"}
        </Text>
        {instructorCourses.length === 0 ? (
          <Text className={styles.messageText}>No departments</Text>
        ) : (
            instructorCourses.map((instructorCourse) => (
            <DepartmentCourseComponent key={instructorCourse.instructor_course_id} iCourse={instructorCourse} dept={dept} course={course} />
          ))
        )}
      </Flex>
    );
}

function DepartmentCourseComponent({ iCourse, dept, course,  }) {

    return (
        <>
        <Table size="sm" textAlign="center">
            <Thead>
            <Tr>
                <Th textAlign="center">Instructor</Th>
                <Th textAlign="center">Time</Th>
                <Th textAlign="center">Days</Th>
                <Th textAlign="center">Credit Hours</Th>
                <Th textAlign="center">Enrollment</Th>
                <Th textAlign="center">Enroll</Th>
            </Tr>
            </Thead>
            <Tbody>
            <Tr>
                <Td textAlign="center">{iCourse.first_name} {iCourse.last_name}</Td>
                <Td textAlign="center">{iCourse.start_time} - {iCourse.end_time}</Td>
                <Td textAlign="center">{iCourse.course_days.join(', ')}</Td>
                <Td textAlign="center">{course.points}</Td>
                <Td textAlign="center">{iCourse.num_enrolled} / {iCourse.max_enrollment}</Td>
                <Td textAlign="center">
                    <Button onClick={
                        () => enrollIntoCourse(iCourse.instructor_course_id)
                    }>
                            Enroll
                    </Button>
                </Td>
            </Tr>
            </Tbody>
        </Table>
        </>
    );
  }