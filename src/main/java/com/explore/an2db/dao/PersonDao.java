package com.explore.an2db.dao;

import com.explore.an2db.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {

    int insertPerson(UUID id, Person person);

    default int insertPerson(Person person){
        UUID id = UUID.randomUUID();
        return insertPerson(id,person);
    }

    List<Person> getAllPerson();

    Optional<Person> getPerson(UUID id);

}
