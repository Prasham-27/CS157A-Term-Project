package com.cs157a.studentmanagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
class StudentManagementApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Test
	public void testSignup() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> request = new HashMap<>();
		request.put("email", "test@example.com");
		request.put("password", "password123");
		request.put("firstName", "John");
		request.put("lastName", "Doe");
		String json = objectMapper.writeValueAsString(request);

		mockMvc.perform(post("/api/signup")
						.contentType(MediaType.APPLICATION_JSON)
						.content(json))
				.andDo(print())
				.andExpect(status().isOk()) // Expect HTTP 200
				.andExpect(content().string("Success"));
	}

}
