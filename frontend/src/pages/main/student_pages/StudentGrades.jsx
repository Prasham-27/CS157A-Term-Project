import React, { useState, useEffect } from "react";
import { 
    Flex, 
    Text, 
    Divider, 
    Grid,
    GridItem,
    HStack,
    VStack
    } from "@chakra-ui/react";
import { Link } from "react-router-dom";
import { EnrollmentStatus } from "../../../enums/enums.js"
import styles from "./StudentMyCourses.module.css"
import axiosInstance from "../../../axiosInstance";

export default function StudentGrades() {

    const [ courses, setCourses ] = useState([]);
    const [ gpa, setGpa ] = useState([]);

    const getGrades = async () => {
        try {
            let response = await axiosInstance.get("/api/student/grades");
            console.log(response.data);
            if (response.status === 200) {  
                setCourses(response.data.courses);
                setGpa(response.data.gpa);
            }
        } 
        catch (error) {
            console.error(error);
        }
    }

        // Get the student's graded courses
        useEffect(() => {
            getGrades();
        }, []);

    return (
        <Flex className={styles.container}>
        <Text className={styles.statusText} color="#0055A2">
          Completed Courses
        </Text>
        {courses.length === 0 ? (
          <Text className={styles.messageText}>
            None completed
          </Text>
        ) : (
            <>
            <br/>
            <Text className={styles.statusText} color="#0055A2">
              Total GPA: {gpa.toFixed(2)}
            </Text>
            <br/>
            {courses.map((course) => (
              <CourseComponent key={course.enrollment_date} course={course} />
            ))}
          </>
        )}
      </Flex>
    );
}

function CourseComponent({ course }) {
    return (
      <> 
        <VStack>
            <HStack>
                <Text className={styles.heading}>
                {course.dept_abbreviation} {course.course_number}: {course.course_name}
                </Text>
            </HStack>
            <Grid className={styles.grid}>
                <GridItem className={styles.gridItem}>
                    <Text className={styles.infoText}>Instructor</Text>
                    <Text>{course.instructor_first_name} {course.instructor_last_name}</Text>
                </GridItem>
                <GridItem className={styles.gridItem}>
                    <Text className={styles.infoText}>Points</Text>
                    <Text>{course.points}</Text>
                </GridItem>
                <GridItem className={styles.gridItem}>
                    <Text className={styles.infoText}>Grade</Text>
                    <Text>{
                            (() => {
                                switch (course.grade) {
                                  case 'A_MINUS':
                                    return 'A-';
                                  case 'B_PLUS':
                                    return 'B+';
                                  case 'B_MINUS':
                                    return 'B-';
                                  case 'C_PLUS':
                                    return 'C+'
                                  case 'C_MINUS':
                                  default:
                                    return 'N/A';
                                }
                              })()
                            }
                    </Text>
                </GridItem>
            </Grid>     
        </VStack>
      </>
    );
  }