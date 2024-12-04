
import InstructorSideBar from '../../components/InstructorSideBar'
import { Flex } from "@chakra-ui/react";
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