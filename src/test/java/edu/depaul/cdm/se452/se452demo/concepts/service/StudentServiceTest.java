package edu.depaul.cdm.se452.se452demo.concepts.service;

import org.hamcrest.CoreMatchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.depaul.cdm.se452.se452demo.concepts.relational.basic.Student;
import edu.depaul.cdm.se452.se452demo.concepts.relational.basic.StudentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

// Ensure there is no port conflict when running JUnit test
@SpringBootTest
@AutoConfigureMockMvc
public class StudentServiceTest {
    private static final String STUDENT_URL = "/api/db/students";

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getAllStudents() throws Exception {
		// when - action
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get(STUDENT_URL));


		var recordCount = (int) studentRepository.count();
		
		// then - verify the output
		response.andExpect(MockMvcResultMatchers.status().isOk());
		response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(recordCount)));
    }

	@Autowired
	private ObjectMapper objectMapper;

    @Test
    public void addStudent() throws Exception {
		// given - setup or precondition
		Student student = Student.builder().name("peter parker").email("peterparker@nyu.edu").age(20).build();
		String studentAsJson = objectMapper.writeValueAsString(student);

		long beforeSize = studentRepository.count();

		// when - action
		var request = MockMvcRequestBuilders.post(STUDENT_URL);
		request.contentType(MediaType.APPLICATION_JSON);
		request.content(studentAsJson);
		ResultActions response = mockMvc.perform(request);

		var jsonResponse = response.andReturn().getResponse().getContentAsString();
		// then - verify the output
		Number updatedStudent = new ObjectMapper().readValue(jsonResponse, Number.class);

		response.andExpect(MockMvcResultMatchers.status().isOk());
		assertNotEquals(updatedStudent, student.getId());
    }

	@Test
    public void removeStudent() throws Exception {
		// given - setup or precondition
		long beforeSize = studentRepository.count();

		// when - action
		var request = MockMvcRequestBuilders.delete(STUDENT_URL+"/1");
		ResultActions response = mockMvc.perform(request);

		long afterSize = studentRepository.count();

		response.andExpect(MockMvcResultMatchers.status().isOk());
    }



}
