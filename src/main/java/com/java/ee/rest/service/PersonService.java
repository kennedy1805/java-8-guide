package com.java.ee.rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.java.ee.rest.object.model.Person;

@Stateless
public class PersonService {

	public List<Person> findPersons(String name) {
		List<Person> persons = new ArrayList<>();
		return persons;
	}

	public Person findById(Long id) {
		return new Person();
	}

	public Person add(Person person) {
		return person;
	}

	public Person update(Long personId, Person person) {
		return person;
	}

	public void remove(Long id) {
		
	}

}
