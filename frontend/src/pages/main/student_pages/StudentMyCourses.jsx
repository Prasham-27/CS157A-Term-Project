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


export default function StudentMyCourses() {

    const [ droppedCourses, setDroppedCourses ] = useState([]);
    const [ enrolledCourses, setEnrolledCourses ] = useState([]);

    const getMyCourses = async () => {
        try {
            let response = await axiosInstance.get("/api/student/mycourses");
            console.log(response.data);
            if (response.status === 200) {  
                const enrolled = [];
                const dropped = [];
                response.data.forEach(
                    (courseInfo) => {
                        if (courseInfo.enrollment_status === EnrollmentStatus.ENROLLED)
                            enrolled.push(courseInfo);
                        else if (courseInfo.enrollment_status === EnrollmentStatus.DROPPED)
                            dropped.push(courseInfo);
                    }
                );
                setDroppedCourses(dropped);
                setEnrolledCourses(enrolled);
            }
        } 
        catch (error) {
            console.error(error);
        }
    }

    // Get the student's courses
    useEffect(() => {
        getMyCourses();
    }, []);

    return (
        <Flex className={styles.container}>
        <Text className={styles.statusText} color="#0055A2">
          Enrolled
        </Text>
        {enrolledCourses.length === 0 ? (
          <Text className={styles.messageText}>
            None enrolled
          </Text>
        ) : (
            enrolledCourses.map((course) => <CourseComponent key={course.enrollment_date} course={course} />)
        )}

        <Text className={styles.statusText} color="#0055A2">
          Dropped
        </Text>
        {droppedCourses.length === 0 ? (
          <Text className={styles.messageText}>
            None dropped
          </Text>
        ) : (
            droppedCourses.map((course) => <CourseComponent key={course.enrollment_date} course={course} />)
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
                    <Text className={styles.infoText}>Time</Text>
                    <Text>{course.start_time} - {course.end_time}</Text>
                </GridItem>
                <GridItem className={styles.gridItem}>
                    <Text className={styles.infoText}>Days</Text>
                    <Text>{course.course_days.join(",")}</Text>
                </GridItem>
                <GridItem className={styles.gridItem}>
                    <Text className={styles.infoText}>Points</Text>
                    <Text>{course.points}</Text>
                </GridItem>
            </Grid>     
        </VStack>
      </>
    );
  }
  