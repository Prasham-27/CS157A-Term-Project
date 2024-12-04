import StudentSideBar from "../../components/StudentSideBar";
import { Flex } from "@chakra-ui/react";
import styles from "./Student.module.css"

export default function Student() {
    return (
        <Flex className={styles.container}>
          <Flex className={styles.menuContent}>
            <StudentSideBar/>
          </Flex>
        </Flex>
      );
}