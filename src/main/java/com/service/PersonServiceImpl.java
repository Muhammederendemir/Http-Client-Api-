package com.service;

import com.dao.PersonDAO;
import com.dao.PersonDAOImpl;
import com.model.Person;

import java.util.List;

public class PersonServiceImpl implements PersonService {
    PersonDAO personDAO = new PersonDAOImpl();

    public Person findPerson(int id) {
        return personDAO.getPersonById(id);
    }

    public void createPerson(Person person) {
        personDAO.savePerson(person);
    }

    public void updatePerson(Person person) {
        personDAO.updatePerson(person);
    }

    public void deletePerson(int id) {
        personDAO.deletePersonById(id);
    }
}
