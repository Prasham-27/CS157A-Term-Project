package com.cs157a.studentmanagement;

import com.cs157a.studentmanagement.model.Roles;
import com.cs157a.studentmanagement.service.UsersService;
import com.cs157a.studentmanagement.utils.enums.Role;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
class StudentManagementApplicationTests {

	@Autowired
	private MockMvc mockMvc;

   @Mock
   private UsersService usersService;
	@Test
	public void testSignup() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> request = new HashMap<>();
      HashMap<String, String> names = new HashMap<>();
      names.put("John", "Doe");
      names.put("Jane", "Doe");

      // Even = Student, odd = Instructor
      int i = 0;
      for (Map.Entry<String, String> name : names.entrySet()) {
         request.put("email", String.format("%s.%s@myschool.edu",
                 name.getKey().toLowerCase(), name.getValue().toLowerCase()));
         request.put("password", "password123");
         request.put("first_name", String.format("%s", name.getKey()));
         request.put("last_name", String.format("%s", name.getValue()));
         String json = objectMapper.writeValueAsString(request);

         if (i % 2 == 0) {
            mockMvc.perform(post("/api/signup/student")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andDo(print())
                    .andExpect(status().isOk()) // Expect HTTP 200
                    .andExpect(content().string("Success"));
         }
         else {
            mockMvc.perform(post("/api/signup/instructor")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andDo(print())
                    .andExpect(status().isOk()) // Expect HTTP 200
                    .andExpect(content().string("Success"));
         }

         i++;
      }
	}

   /**
    * Tests that the logout endpoint correctly invalidates the session
    *
    * @throws Exception
    */
   @Test
   public void checkLogout() throws Exception {
      MockHttpSession session = new MockHttpSession();
      session.setAttribute("id", 123L);
      session.setAttribute("role", Role.INSTRUCTOR);

      // Logout
      MvcResult result = mockMvc.perform(get("/api/logout")
              .session(session)).andReturn();

      // Print the return status
      int status = result.getResponse().getStatus();
      System.out.println("HTTP Status Code: " + status);

      // Print the response content
      String content = result.getResponse().getContentAsString();
      System.out.println("Response Content: " + content);

      assertEquals(200, status);
      assertThrows(IllegalStateException.class, () -> session.getAttribute("id"));
   }

   @Test
   @WithMockUser(username = "6", roles = "STUDENT")
   public void checkLogin() throws Exception {
      // Arrange: Mock the UsersService behavior
      Long userId = 6L;
      String password = "password123";
      Role role = Role.STUDENT;  // Adjust to your actual Role enum

      // Create the request payload
      String jsonPayload = "{ \"user_id\": \"" + userId + "\", \"password\": \""
              + password + "\" }";

      // Perform the login request using MockMvc
      mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                      .contentType("application/json")
                      .content(jsonPayload))
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.content().string("Success"));
   }


   /**
    * A student CAN access a student endpoint.
    *
    * @throws Exception
    */
   @Test
   @WithMockUser(username = "6", roles = "STUDENT")
   public void testStudentEndpointSecurityAgainstStudent() throws Exception {

      // -- That a student can use the endpoint --
      MockHttpSession studentSession = new MockHttpSession();
      MvcResult studentResult = mockMvc.perform(get("/api/student/test")
              .session(studentSession)).andReturn();

      // Print the return status
      int studentStatus = studentResult.getResponse().getStatus();
      System.out.println("HTTP Status Code: " + studentStatus);

      // Print the response content
      String studentContent = studentResult.getResponse().getContentAsString();
      System.out.println("Response Content: " + studentContent);

      // Expect authorized
      assertEquals(200, studentStatus);

   }

   /**
    * Check that an instructor CAN't access a student endpoint
    *
    * @throws Exception
    */
   @Test
   @WithMockUser(username = "7", roles = "INSTRUCTOR")
   public void testStudentEndpointSecurityAgainstInstructor() throws Exception {

      // -- That a student can use the endpoint --
      MockHttpSession session = new MockHttpSession();
      MvcResult studentResult = mockMvc.perform(get("/api/student/test")
              .session(session)).andReturn();

      // Print the return status
      int status = studentResult.getResponse().getStatus();
      System.out.println("HTTP Status Code: " + status);

      // Print the response content
      String content = studentResult.getResponse().getContentAsString();
      System.out.println("Response Content: " + content);

      // Expect authorized
      assertEquals(403, status);
   }
}
