import { Button } from "@chakra-ui/react";
import React from "react";
import axiosInstance from "../axiosInstance";

export default function LogoutButton() {
    return (
        <Button

        onClick={
            async () => {
                try {
                    let response = await axiosInstance.post(`/api/logout`);
                    console.log(response);
                    if (response.status !== 200)
                        return;
                }
                catch (err) {
                    console.error(err);
                }
            }
        }
        >
            <a href="/">Log Out</a>
        </Button>
    );
}