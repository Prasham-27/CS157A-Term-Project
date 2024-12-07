
import InstructorSideBar from '../../components/InstructorSideBar'
import { Flex, Button } from "@chakra-ui/react";
import axiosInstance from '../../axiosInstance';
import styles from "./Student.module.css"

export default function Instructor() {


  const test = async (event) => {
    try {
      let response = await axiosInstance('/api/instructor/test');
      if (response.status === 200) {
        console.log("SUCCESS!");
      }
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
            <InstructorSideBar/>
          </Flex>
        </Flex>
      );
}