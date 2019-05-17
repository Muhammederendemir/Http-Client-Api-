package com.dao;

import com.model.Person;
import org.json.JSONObject;

import java.util.List;

public interface PersonDAO {
    void savePerson(Person person) throws Exception;

    Person getPersonById(int id) throws Exception;

    void updatePerson(Person person) throws Exception;

    void deletePersonById(int id) throws Exception;
}
