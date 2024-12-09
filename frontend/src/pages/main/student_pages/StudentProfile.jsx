import React, { useState, useEffect } from "react";
import {
  Flex,
  Text,
  Icon,
  FormControl,
  FormLabel,
  Input,
  Button,
  VStack,
  Divider
} from "@chakra-ui/react";
import styles from "../ProfilePage.module.css";

import axiosInstance from "../../../axiosInstance";


export default function StudentProfile() {

    const [ studentInfo, setStudentInfo ] = useState({});

    const getGrades = async () => {
        try {
            let response = await axiosInstance.get("/api/student/info");
            console.log(response.data);
            if (response.status === 200) {  
                setStudentInfo(response.data)
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
          <Flex className={styles.bodyContainer}>
            <Text className={styles.headerText}>Student Profile</Text>
            <VStack>
                <Text className={styles.headerText}> Name </Text>
                <Text className={styles.formText}> {studentInfo.first_name} {studentInfo.last_name}</Text>
                <Divider/>
                <Text className={styles.headerText}> Email </Text>
                <Text className={styles.formText}> {studentInfo.email}</Text>
                <Divider/>
                <Text className={styles.headerText}> Major </Text>
                <Text className={styles.formText}> {studentInfo.major}</Text>
                <Divider/>
                <Text className={styles.headerText}> Academic Year </Text>
                <Text className={styles.formText}> {studentInfo. academic_year}</Text>
            </VStack>
          </Flex>
        </Flex>
      );
}