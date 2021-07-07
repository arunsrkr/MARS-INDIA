package com.person.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.person.exception.PersonException;
import com.person.model.Person;
import com.person.repository.PersonRepository;

@Repository
public class PersonService implements PersonInterface {
	private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

	@Autowired
	PersonRepository personRepository;

	/**
	 * addPerson(Person) method will take Person Object as an input and it will
	 * communicate with Repository and insert the Person details into database and
	 * it will return Person Object
	 */

	@Override
	public Person addPerson(Person person) throws PersonException {
		logger.info("Add Person Person Id : " + person.getId());
		Person person1 = null;
		try {
			
			person1 = personRepository.save(person);
		} catch (Exception e) {
			throw new PersonException("Error in addPerson Service Layer");
		}
		return person1;
	}

	/**
	 * getAllPersons() method will communicate with Repository and It will return
	 * the List<Persons> Objects from database.
	 */
	@Override
	public List<Person> getAllPersons() throws PersonException {
		logger.info("getAllPersons() method will return list of Persons ");
		List<Person> persons = null;

		try {
			persons = (List<Person>) personRepository.findAll();
		} catch (Exception e) {
			throw new PersonException("Error in addPerson Service Layer");
		}
		return persons;

	}

	/**
	 * editPerson(Person) method will take Person Object as an input and it will
	 * communicate with Repository and it will update the Person details in database
	 * and it will return updated Person Object
	 */
	@Override
	public Person editPerson(Person person) {
		logger.info("Edit Person Person Id : " + person.getId());
		Person person1 = personRepository.findById(person.getId()).get();

		if (person1 != null)
			personRepository.save(person);
		else
			throw new RuntimeException("Record Not Found Error");

		return person1;
	}

	/**
	 * deletePerson(int) method will communicate with Repository and It will delete
	 * the corresponding record from database and it will return a String.
	 */
	@Override
	public String deletePerson(int id) {
		logger.info("Deleting person id : " + id);
		String msg = "Record Not Found";

		Person person = personRepository.findById(id).get();

		if (person != null) {
			personRepository.deleteById(id);
			msg = "Record deleted";
		}

		return msg;
	}

}
