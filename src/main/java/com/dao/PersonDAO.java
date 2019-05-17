package com.dao;

import com.model.Person;
import org.json.JSONObject;

import java.util.List;

public interface PersonDAO {
    void savePerson(Person person);

    Person getPersonById(int id);

    void updatePerson(Person person);

    void deletePersonById(int id);
}
