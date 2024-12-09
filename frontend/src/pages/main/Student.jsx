import StudentSideBar from "../../components/StudentSideBar";
import TopBar from "../../components/TopBar";
import { Flex, Button } from "@chakra-ui/react";
import styles from "./Student.module.css"
import { useParams, Outlet, useLocation } from "react-router-dom";
import axiosInstance from "../../axiosInstance";

export default function Student() {


    return (
        <Flex className={styles.container}>
          <TopBar/>
          <Flex className={styles.menuContent}>
            <StudentSideBar/>
            <Outlet/>
          </Flex>
        </Flex>
      );
}