import { Button, Flex, HStack, VStack, Text } from "@chakra-ui/react"
import { Link } from "react-router-dom";

export default function WelcomePage() {
    return (
        <Flex
        height="100vh"       
        align="center"       
        justify="center"        
      >
        <VStack>
            <Text fontWeight="bold" fontSize="2xl">Welcome!</Text>
            <HStack>
                <Link to="/login">
                <Button colorScheme="blue" size="lg">
                    login
                </Button>
                </Link>
            </HStack>
        </VStack>
      </Flex>
    );
}