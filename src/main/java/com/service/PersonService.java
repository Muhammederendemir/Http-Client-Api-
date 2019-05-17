package com.service;

import com.model.Person;

import java.util.List;

public interface PersonService {
    Person findPerson(int id);//private olacaka

    void createPerson(Person person);

    void updatePerson(Person person);

    void deletePerson(int id);
}
