import axios from "axios"
import { useNavigate } from "react-router-dom";

const axiosInstance = axios.create({
    baseURL: "http://localhost:8080/",
    headers: {
        "Content-type": "application/json"
    },
    withCredentials: true,
});

axiosInstance.interceptors.request.use(
    config => {

        // If the JWT token is in local storage, add it to the header
        const token = sessionStorage.getItem("jwtToken");
        if (token) {
            config.headers["Authorization"] = `Bearer ${token}`;
        }
        return config;
    },
    error => {
        return Promise.reject(error);
    }
);

axiosInstance.interceptors.response.use(
    response => {
        // Resolve the promise for successful responses
        return response;
    },
    error => {

        // Handle expired JWT token
        const originalRequest = error.config;
        if (error.response && error.response.status === 401 && !originalRequest._retry) {

            // Destroy the JWT token and destroy session all together
            sessionStorage.clear();
            sessionStorage.removeItem('jwtToken');

            alert("Your session has expired. Please log in again.");
            
            const navigate = useNavigate();
            navigate("/"); // Navigate back to root

            return Promise.reject(error);
        }

        // For responses with status code 400-499, resolve the promise with the response
        // This means that requests that return 4xx status code won't get caught by a `catch` block
        if (error.response && error.response.status >= 400 && error.response.status < 500) {
            return Promise.resolve(error.response);
        }
        // For other errors, reject the promise with the error
        return Promise.reject(error);
    }
);

export default axiosInstance;
