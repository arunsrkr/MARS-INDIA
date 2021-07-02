package com.person;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.person.controller.PersonController;
import com.person.exception.PersonException;
import com.person.model.Person;
import com.person.service.PersonInterface;

@SpringBootApplication
public class PersonAppApplication implements CommandLineRunner {

	@Autowired
	PersonInterface service;
	
	@Autowired
	PersonController controller;

	private static final Logger logger = LoggerFactory.getLogger(PersonAppApplication.class);

	public static void main(String[] args) {
		logger.info("This is PersonAppApplication class");
		SpringApplication.run(PersonAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws PersonException, IOException {

		Scanner sc = new Scanner(System.in);
		boolean flag = true;

		while (flag) {

			System.out.println("1. Add Person");
			System.out.println("2. Edit Person");
			System.out.println("3. Delete Person");
			System.out.println("4. Count Number of Persons");
			System.out.println("5. List Persons");
			System.out.println("6. Exit");

			System.out.println("Enter your choice (1 to 6) :");
			int choice = sc.nextInt();

			switch (choice) {

			case 1:
				boolean addFlag = false;
				try {
					
					//String request = (new ObjectMapper()).writeValueAsString(person);
					//controller.addPerson(request);
					ObjectMapper mapper = new ObjectMapper();
					TypeReference<Person> typeReference = new TypeReference<Person>(){};
					InputStream inputStream = TypeReference.class.getResourceAsStream("/json/person.json");
					Person person = mapper.readValue(inputStream,typeReference);
					service.addPerson(person);

				} catch (Exception e) {
					addFlag = true;
					e.printStackTrace();
					logger.error("Exception occured in addPerson() method");
				}

				if (!addFlag)
					System.out.println("Record Inserted into Database Successfully");
				break;

			case 2:

				boolean editFlag = false;

				try {
					ObjectMapper mapper = new ObjectMapper();
					TypeReference<Person> typeReference = new TypeReference<Person>(){};
					InputStream inputStream = TypeReference.class.getResourceAsStream("/json/person1.json");
					 Person person = mapper.readValue(inputStream,typeReference);

					service.editPerson(person);
				} catch (Exception e) {
					editFlag = true;
					System.out.println("Error in editPerson method");
					logger.error("Error in editPerson method");
				}
				if (!editFlag)
					System.out.println("Record updated Successfully");
				break;

			case 3:
				boolean deleteFlag = false;
				System.out.println("Enter Person Id to delete");
				int id = sc.nextInt();

				try {
					service.deletePerson(id);
				} catch (Exception e) {
					deleteFlag = true;
					System.out.println("Error in deletePerson method");
					logger.error("Error in deletePerson method");

				}
				if (!deleteFlag)
					System.out.println("Record deleted Successfully");
				break;

			case 4:
				try {
					int count = service.getAllPersons().size();
					System.out.println("Number of Persons = " + count);
				} catch (Exception e) {
					System.out.println("Error in getNumberOfPersons method");
					logger.error("Error in getNumberOfPersons method");
				}
				break;
			case 5:

				try {
					List<Person> persons = service.getAllPersons();

					for (Person person2 : persons)
						System.out
								.println(person2.getId() + "--" + person2.getFirstName() + "--" + person2.getSurName());
				} catch (Exception e) {
					System.out.println("Error in getAllPersons method ");
					logger.error("Error in getAllPersons method ");
				}
				break;
			case 6:
				System.exit(0);

			}

			System.out.println("Do you want to continue (y/n)");
			char ch = sc.next().charAt(0);

			if (ch == 'y' || ch == 'Y')
				flag = true;
			else {
				flag = false;
				System.out.println("Thanks for using the Application.");
			}

		}

	}

}
