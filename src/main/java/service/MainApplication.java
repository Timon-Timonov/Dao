package service;

import connection.Connector;
import dao.PersonDao;
import dao.PersonDaoImpl;
import dto.Person;

import java.sql.SQLException;
import java.util.List;

public class MainApplication {
	public static void main(String[] args) throws SQLException {

		try (Connector connector = Connector.getInstance()) {

			PersonDao myDao = new PersonDaoImpl(connector);

			//id for demonstrtion of work of methods: get(), update(), delete()
			int id = 3;

			//selectAll
			printAllDataBase(myDao);

			//save
			System.out.println("Demonstration of method save():");
			Person max = Person.builder()
					.name("Mikle")
					.surname("Jordan")
					.id(155)
					.build();
			Person savedPerson = myDao.save(max);
			System.out.println("Saved person:");
			System.out.println(savedPerson);
			printAllDataBase(myDao);

			//get
			System.out.println("Demonstration of method get(" + id + "):");
			Person personById = myDao.get(String.valueOf(id));
			System.out.println();
			System.out.println(personById.getId() != 0 ? personById : "There is no person with such id.");
			System.out.println("--------------------------------------------");

			//get second try
			System.out.println("Demonstration of method get(" + (id + 1) + "):");
			personById = myDao.get(String.valueOf(id + 1));
			System.out.println();
			System.out.println(personById != null ? personById : "There is no person with such id.");
			System.out.println("--------------------------------------------");


			//update
			Person ivan = Person.builder()
					.name("David")
					.surname("Guetta")
					.id(id + 10)
					.build();
			System.out.println("Demonstration of method update(" + ivan + "):");
			Person updated = myDao.update(ivan);
			if (updated != null) {
				System.out.println("Update successful.");
				printAllDataBase(myDao);
			} else {
				System.out.println("There is no persons with such id!");
				System.out.println("--------------------------------------------");
				System.out.println();
			}

			//update second try
			ivan.setId(id + 11);
			System.out.println("Demonstration of method update(" + ivan + "):");
			updated = myDao.update(ivan);
			if (updated != null) {
				System.out.println("Update successful.");
				printAllDataBase(myDao);
			} else {
				System.out.println("There is no persons with such id!");
				System.out.println("--------------------------------------------");
				System.out.println();
			}


			//delete
			System.out.println("Demonstration of method delete(" + (id + 10) + "):");
			Person deleted = myDao.delete(String.valueOf(id + 10));
			if (deleted != null) {
				System.out.println("Delete successful.");
				System.out.println("Deleted " + deleted);
				printAllDataBase(myDao);
			} else {
				System.out.println("There is no persons with such id!");
				System.out.println("--------------------------------------------");
				System.out.println();
			}

			//delete second try
			System.out.println("Demonstration of method delete(" + (id + 22) + "):");
			deleted = myDao.delete(String.valueOf(id + 22));

			if (deleted != null) {
				System.out.println("Delete successful.");
				System.out.println("Deleted " + deleted);
				printAllDataBase(myDao);
			} else {
				System.out.println("There is no persons with such id!");
				System.out.println("--------------------------------------------");
				System.out.println();
			}
		}
	}


	private static void printAllDataBase(PersonDao myDao) throws SQLException {
		List<Person> dbContent = myDao.selectAll("Person");
		System.out.println();
		System.out.println("All content of database(demonstration of method selectAll()):");
		for (Person person : dbContent) {
			System.out.println(person);
		}
		System.out.println("--------------------------------------------");
		System.out.println();
	}
}

