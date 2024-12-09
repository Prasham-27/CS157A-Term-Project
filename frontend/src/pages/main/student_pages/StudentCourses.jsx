import { useLocation, useParams, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import {
    Flex,
    Text,
    Card, 
    CardHeader, 
    CardBody, 
    CardFooter,
    Divider,
    Box,
    Menu,
    MenuButton,
    MenuList,
    MenuItem,
    Button
  } from "@chakra-ui/react";
import styles from "./StudentMyCourses.module.css";
import axiosInstance from "../../../axiosInstance";


export default function StudentCourses() {
    const { dept_id } = useParams();
    const location = useLocation();
    const [deptCourses, setDeptCourses] = useState([]);
    const dept = location.state?.dept;

    const getDepartmentCourses = async () => {
        try {
          let response = await axiosInstance.get(`/api/student/departments/${dept_id}/courses`);
          console.log(response.data);
          if (response.status === 200) {
            setDeptCourses(response.data);
          }
        } catch (error) {
          console.error(error);
        }
      };

    useEffect(() => {
        getDepartmentCourses();
    }, []);

    return (
        <Flex className={styles.container}>
        <Text className={styles.statusText} color="#0055A2">
        {dept ? dept.dept_name : "No department selected"}
        </Text>
        {deptCourses.length === 0 ? (
          <Text className={styles.messageText}>No departments</Text>
        ) : (
            deptCourses.map((course) => (
            <DepartmentCourseComponent key={course.course_id} dept={dept} course={course} />
          ))
        )}
      </Flex>
    );
}

function DepartmentCourseComponent({ dept, course }) {

    const navigate = useNavigate(); 
  
    const handleCourseClick = () => {
      navigate(`${course.course_id}`, { state: { dept, course } });
    };
  
    return (
      <>
          <Button 
              onClick={handleCourseClick}
              border="3px solid grey"
              width="600px"
              height="40px"
              display="flex"
              justifyContent="center"
              alignItems="center"
          >
          <Flex>
              <Card
                  border="3px solid grey"
                  width="600px"
                  height="40px"
                  display="flex"
                  justifyContent="center"
                  alignItems="center"
              >
                  <Box flex="1">
                  <CardHeader
                      fontWeight="bold"
                      textAlign="center"
                      fontSize="medium"
                      padding="1px"
                      margin="5px"
                      overflow="hidden"
                  >
                      {dept.dept_abbreviation}: {course.course_num} {course.course_name}
                  </CardHeader>
                  </Box>
              </Card>
          </Flex>
          </Button>
          <br />
      </>
    );
  }