package com.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.person.controller.PersonController;
import com.person.exception.PersonException;
import com.person.model.Person;
import com.person.service.PersonService;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
class PersonAppApplicationTests {

	@Test
	void contextLoads() {
	}

	@MockBean
	private PersonService service;

	@Test
	public void addPersonTest() throws PersonException {
		Person person1 = new Person();
		person1.setId(1002);
		person1.setFirstName("Arun");
		person1.setSurName("Kumar");

		Person person2 = new Person();
		person1.setId(1002);
		person1.setFirstName("Arun");
		person1.setSurName("Kumar");

		given(service.addPerson(person1)).willReturn(person2);
		assertEquals(person2.getId(), 1002);
	}

	@Test
	public void editPerson() {
		Person person1 = new Person();
		person1.setId(1003);
		person1.setFirstName("Arun");
		person1.setSurName("Kumar");

		Person person2 = new Person();
		person1.setId(1004);
		person1.setFirstName("Arun");
		person1.setSurName("Kumar");
		given(service.editPerson(person1)).willReturn(person2);
		assertEquals(person2.getId(), 1004);
	}

	@Test
	public void deletePersonTest() throws PersonException {

		given(service.deletePerson(1001)).willReturn("Record deleted");
		assertEquals(service.deletePerson(1001), "Record deleted");
	}

	@Test
	public void getAllPersonsTest() throws Exception {
		Person person1 = new Person();
		person1.setId(1001);
		person1.setFirstName("Arun");
		person1.setSurName("Kumar");

		List<Person> persons = new ArrayList<>();
		persons.add(person1);

		given(service.getAllPersons()).willReturn(persons);
		assertEquals(service.getAllPersons().get(0).getId(), 1001);

	}

}
