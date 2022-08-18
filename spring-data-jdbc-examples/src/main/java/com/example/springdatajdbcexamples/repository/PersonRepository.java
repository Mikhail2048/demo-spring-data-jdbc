package com.example.springdatajdbcexamples.repository;

import com.example.springdatajdbcexamples.models.simple.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

    Person findByAgeBefore(Integer ageTopThreshold);
}