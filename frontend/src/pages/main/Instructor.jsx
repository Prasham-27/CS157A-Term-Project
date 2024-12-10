
import InstructorSideBar from '../../components/InstructorSideBar'
import { Flex, Button } from "@chakra-ui/react";
import axiosInstance from '../../axiosInstance';
import { useParams, Outlet, useLocation } from "react-router-dom";
import TopBar from "../../components/TopBar";
import styles from "./Student.module.css"

export default function Instructor() {


    return (
        <Flex className={styles.container}>
          <TopBar/>
          <Flex className={styles.menuContent}>
            <InstructorSideBar/>
            <Outlet/>
          </Flex>
        </Flex>
      );
}