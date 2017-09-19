package com.postgres.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.postgres.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, String> {

}
