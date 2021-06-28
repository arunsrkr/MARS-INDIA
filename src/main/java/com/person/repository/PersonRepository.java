package com.person.repository;

import org.springframework.data.repository.CrudRepository;

import com.person.model.Person;

public interface PersonRepository  extends CrudRepository<Person, Integer>{

}
