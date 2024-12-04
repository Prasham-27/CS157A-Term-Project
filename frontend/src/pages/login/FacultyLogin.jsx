import axiosInstance from "../../axiosInstance";
import Login from "./Login"

// TODO if we add one more role, might have to be able to switch between functions
export default function FacultyLogin() {

    function instructorLoginApi(data) {
        return axiosInstance.post("/api/login/instructor", data);
    }

    return (<Login loginApiFunction={instructorLoginApi} redirectPath="/instructor" />);
}