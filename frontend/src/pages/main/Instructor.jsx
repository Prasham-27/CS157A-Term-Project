
import InstructorSideBar from '../../components/InstructorSideBar'
import { Flex, Button } from "@chakra-ui/react";
import axiosInstance from '../../axiosInstance';
import styles from "./Student.module.css"

export default function Instructor() {


    return (
        <Flex className={styles.container}>
          <Flex className={styles.menuContent}>
            <InstructorSideBar/>
          </Flex>
        </Flex>
      );
}