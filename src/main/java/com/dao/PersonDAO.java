package com.dao;

import com.model.Person;

public interface PersonDAO {
    void savePerson(Person person) throws Exception;

    Person getPersonById(int id);

    void updatePerson(Person person) throws Exception;

    void deletePersonById(int id) throws Exception;
}
