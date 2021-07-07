package com.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.person.controller.PersonController;
import com.person.model.Person;
import com.person.service.PersonService;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
class PersonAppApplicationTests {

	@MockBean
	private PersonService service;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/**
	 * addPersonTest() method will POST method.
	 */

	@Test
	public void addPersonTest() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		Person person = new Person();
		person.setFirstName("arun");
		person.setSurName("kumar");
		String body = (new ObjectMapper()).writeValueAsString(person);
		MvcResult mvcResult = mockMvc
				.perform(post("/person").content(body).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		int status = mvcResult.getResponse().getStatus();

		assertEquals(status, 200);

	}

	/**
	 * editPersonTest() method will test PUT method.
	 */
	@Test
	public void editPersonTest() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		Person person = new Person();
		person.setFirstName("arun");
		person.setSurName("kumar");
		String body = (new ObjectMapper()).writeValueAsString(person);
		MvcResult mvcResult = mockMvc
				.perform(put("/person").content(body).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		int status = mvcResult.getResponse().getStatus();

		assertEquals(status, 200);

	}

	/**
	 * deletePersonTest() method will test DELETE method.
	 */
	@Test
	public void deletepersonTest() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		MvcResult mvcResult = mockMvc.perform(delete("/person/1")).andExpect(status().isOk())
				.andReturn();

		int status = mvcResult.getResponse().getStatus();

		assertEquals(status, 200);

	}

	/**
	 * getAllPersonsTest() method will test GET method.
	 */
	@Test
	public void getAllPersonsTest() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		MvcResult mvcResult = mockMvc.perform(get("/person/all")).andExpect(status().isOk()).andReturn();

		int status = mvcResult.getResponse().getStatus();

		assertEquals(status, 200);

	}

	/**
	 * getNumberOfPersonsTest() method will test GET method.
	 */
	@Test
	public void getNumberOfPersonsTest() throws Exception { 
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		MvcResult mvcResult = mockMvc.perform(get("/person/all")).andExpect(status().isOk()).andReturn();

		int status = mvcResult.getResponse().getStatus();

		assertEquals(status, 200);

	}

}
