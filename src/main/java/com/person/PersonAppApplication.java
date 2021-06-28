package com.person;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.person.exception.PersonException;
import com.person.model.Person;
import com.person.service.PersonInterface;


@SpringBootApplication
public class PersonAppApplication implements CommandLineRunner {


	
	@Autowired
	PersonInterface service;

	private static final Logger logger = LoggerFactory.getLogger(PersonAppApplication.class);

	public static void main(String[] args) {
		logger.info("This is PersonAppApplication class");
		SpringApplication.run(PersonAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws PersonException {

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
				Person person = new Person();
				System.out.println("Enter Person Id");
				person.setId(sc.nextInt());

				System.out.println("Enter Person First name");
				person.setFirstName(sc.next());

				System.out.println("Enter Person Surname");
				person.setSurName(sc.next());
				try {
					service.addPerson(person);
				} catch (PersonException e) {
					addFlag = true;
					System.out.println(e.getMsg());
					logger.error(e.getMsg());
					
				}

				if (!addFlag)
					System.out.println("Record Inserted into Database Successfully");
				break;

			case 2:

				boolean editFlag = false;
				Person person1 = new Person();
				System.out.println("Enter Person Id");
				person1.setId(sc.nextInt());

				System.out.println("Enter Person firstname to update");
				person1.setFirstName(sc.next());

				System.out.println("Enter Person Surname to update");
				person1.setSurName(sc.next());

				try {
					service.editPerson(person1);
				} catch(Exception e) {
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
					
					for(Person person2:persons)
						System.out.println(person2.getId()+"--"+person2.getFirstName()+"--"+person2.getSurName());
				}
				catch(Exception e) {
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
