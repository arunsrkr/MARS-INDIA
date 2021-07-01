package com.person.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.person.exception.PersonException;
import com.person.model.Person;
import com.person.service.PersonService;

@RestController
@RequestMapping("/api/person")
public class PersonController {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

	@Autowired PersonService personService;
	/*
	 * addPerson(Person) method will take Person Object as an input and it will communicate with Service Layer
	 * and return ResponseEntity Object.
	 * */
	@PostMapping("/addPerson")
	
	public ResponseEntity<String> addPerson(@Valid @RequestBody Person person) throws PersonException {
		logger.info("Request received in addPerson() method");
		Person person1 = personService.addPerson(person);
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	/*
	 * editPerson(Person) method will take Person Object as an input and it will communicate with Service Layer
	 * and return ResponseEntity Object.
	 * */
	
	@PutMapping("/editPerson")
	public ResponseEntity<Person> editPerson(@RequestBody Person person) {
		logger.info("Request received in editPerson() method");
		Person person1 = personService.editPerson(person);
		return new ResponseEntity<Person>(person1, HttpStatus.OK);
	}
	
	/*
	 * deletePerson(int) method will take an integer(id) value as an input and it will communicate with Service Layer
	 * and return ResponseEntity Object.
	 * */
	
	@DeleteMapping("/deleteperson/{id}")
	public ResponseEntity<String> deletePerson(@PathVariable("id") int id) throws PersonException {
		logger.info("Request received in deletePerson() method");
		
		if(id==1001)
			throw new PersonException("Id is not valid");
		String msg = personService.deletePerson(id);
		 
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	
    
	/*
	 * getAllPersons() method will  communicate with Service Layer
	 * and return ResponseEntity Object.
	 * */
	
	@GetMapping("/allpersons")
	public ResponseEntity<List<Person>> getAllPersons() throws PersonException {
		logger.info("Request received in getAllPersons() method");	
		List<Person> persons = personService.getAllPersons();
		
		return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
	}
	
	/*
	 * getNumberOfPersons() method will communicate with Service Layer
	 * and return ResponseEntity Object.
	 * */
	
	@GetMapping("/countall")
	public ResponseEntity<Integer> getNumberOfPersons() throws PersonException {
		logger.info("Request received in getNumberOfPersons() method");
		List<Person> persons = personService.getAllPersons();
		
		return new ResponseEntity<Integer>(persons.size(), HttpStatus.OK);
	}
}
