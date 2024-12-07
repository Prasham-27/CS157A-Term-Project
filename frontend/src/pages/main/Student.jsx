import StudentSideBar from "../../components/StudentSideBar";
import { Flex, Button } from "@chakra-ui/react";
import styles from "./Student.module.css"
import axiosInstance from "../../axiosInstance";

export default function Student() {

  const test = async (event) => {
    try {
      let response = await axiosInstance('/api/student/test');
      console.log(response.status);
      } catch (error) {
        console.log(error);
    }
    };

    return (
        <Flex className={styles.container}>
          <Button onClick={test}>
            Test
          </Button>
          <Flex className={styles.menuContent}>
            <StudentSideBar/>
          </Flex>
        </Flex>
      );
}