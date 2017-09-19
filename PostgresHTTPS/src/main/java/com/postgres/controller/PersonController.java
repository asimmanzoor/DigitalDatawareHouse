package com.postgres.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.postgres.model.Person;
import com.postgres.repository.PersonRepository;

@RestController
public class PersonController {
	
	@Autowired
	PersonRepository personRepository;
	
	@PostMapping(value="/persons")
	public void savePersons(@RequestBody List<Person> persons) {
		personRepository.save(persons);
	}

	@PostMapping(value="/person")
	public void savePerson(@RequestBody Person person) {
		personRepository.save(person);
	}
	@GetMapping(value="/persons")
	public List<Person> findAllPerson() {
		return (List<Person>)personRepository.findAll();
	}
}
