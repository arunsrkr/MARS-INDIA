package com.person.service;

import java.util.List;

import com.person.exception.PersonException;
import com.person.model.Person;

public interface PersonInterface {
	public Person addPerson(Person person) throws PersonException;
	public List<Person> getAllPersons() throws PersonException;
	public Person editPerson(Person person);
	public String deletePerson(int id);
}
