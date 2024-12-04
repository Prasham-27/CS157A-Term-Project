import axiosInstance from "../../axiosInstance";
import Login from "./Login"

export default function StudentLogin() {

  function studentLoginApi(data) {
    return axiosInstance.post("/api/login/student", data);
  }

  return (<Login loginApiFunction={studentLoginApi} redirectPath="/student" />);
}
