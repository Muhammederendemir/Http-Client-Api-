package com.dao;
import org.json.JSONObject;

public interface PersonDAO {
    String savePerson(JSONObject jsonObject);

    String getPersonById(JSONObject jsonObject);

    String updatePerson(JSONObject jsonObject);

    String deletePersonById(JSONObject jsonObject);
}
