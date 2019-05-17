package com.dao;

import com.model.Person;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


public class PersonDAOImpl implements PersonDAO {
    private final String GET_PERSON_ID = "SELECT * FROM person WHERE personId=?";
    private final String SAVE_PERSON = "INSERT INTO person(personId,personName,personSurname) VALUES(?,?,?)";
    private final String UPDATE_PERSON = "UPDATE person SET personName=?,personSurname=? WHERE personId=? ";
    private final String DELETE_PERSON = "DELETE FROM person WHERE personId=?";
    Person person = new Person();
    Connection connection;
    //Logger logger=Logger.getLogger(PersonDAO.class);

    public PersonDAOImpl() {
        //this.connection = getConnection();
        this.connection = getConnection();
    }

    private Connection getConnection() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = getClass().getResourceAsStream("/database.properties");
            //input = ClassLoader.getSystemClassLoader().getResourceAsStream("database.properties");
            prop.load(input);
        } catch (IOException io) {
            //  logger.error("database.properties bulununamadı  HATA= "+io);
            io.printStackTrace();
        }

        String JDBC_URL = prop.getProperty("jdbc.url");
        String JDBC_USERNAME = prop.getProperty("jdbc.username");
        String JDBC_PASSWORD = prop.getProperty("jdbc.password");

        Properties properties = new Properties();
        properties.setProperty("user", JDBC_USERNAME);
        properties.setProperty("password", JDBC_PASSWORD);
        properties.setProperty("useUnicode", "yes");
        properties.setProperty("characterEncoding", "UTF-8");
        properties.setProperty("serverTimezone", "UTC");
        properties.setProperty("autoReconnect", "true");
        properties.setProperty("useSSL", "false");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return connection = DriverManager.getConnection(JDBC_URL, properties);
        } catch (Exception e) {
            // logger.error("Drive manager bulunamadı HATA= "+e);
            System.out.println("Error : " + e);
        }
        return null;
    }

    public void parse(String data) {

        JSONObject object = new JSONObject(data);

        person.setId(object.getInt("id"));
        person.setName(object.getString("name"));
        person.setSurname(object.getString("surname"));
    }

    public void parseId(String data) {

        JSONObject object = new JSONObject(data);

        person.setId(object.getInt("id"));
        //person.setName(object.getString("name"));
        //person.setSurname(object.getString("surname"));
    }

    public String savePerson(JSONObject jsonObject) {
        String insertdata = jsonObject.toString();
        parse(insertdata);

        String sendString = "bir hata meydana geldi";
        try {
            execute(SAVE_PERSON, person.getId(), person.getName(), person.getSurname());
            sendString = "islem basarili";
        } catch (Exception e) {
            //logger.warn("savePerson da hata ile karşılaşıldı.  HATA= "+e);
            System.out.println("save person  " + e);
        }

        return sendString;
    }

    public String getPersonById(JSONObject jsonObject) {
         String insertdata = jsonObject.toString();
        String sendString = "getPersonById de hata bulunmaktadır.";
         parseId(insertdata);
        //JSONArray jsonArray = null;
         try
         {
             //statement=connection.prepareStatement(selectRc);
             ResultSet resultSet = executeQuery(GET_PERSON_ID, person.getId());
             while(resultSet.next())
             {

                 JSONObject jsonObjectOut = new JSONObject();
                 jsonObjectOut.put("id", resultSet.getInt("personId"));
                 jsonObjectOut.put("name", resultSet.getString("personName"));
                 jsonObjectOut.put("surname", resultSet.getString("personSurname"));
                 //jsonArray.add(jsonObjectOut);//hata burada maven da
                 sendString = jsonObjectOut.toString();
             }
         }catch(Exception e){
             System.out.println("hata");

             // logger.warn("getPersonById de hata ile karşılaşıldı.  HATA= "+e);

         }

        return sendString;
    }

    public String updatePerson(JSONObject jsonObject) {
        String insertdata = jsonObject.toString();
        parse(insertdata);

        String sendString = "bir hata meydana geldi";
        try {
            execute(UPDATE_PERSON, person.getName(), person.getSurname(), person.getId());
            sendString = "islem basarili";
        } catch (Exception e) {
            //logger.warn("updatePerson da hata ile karşılaşıldı.  HATA= "+e);
            System.out.println("save person  " + e);
        }
        return sendString;
    }

    public String deletePersonById(JSONObject jsonObject) {
        String insertdata = jsonObject.toString();
        parseId(insertdata);

        String sendString = "bir hata meydana geldi";
        try {
            execute(DELETE_PERSON, person.getId());
            sendString = "islem basarili";
        } catch (Exception e) {
            //logger.warn("deletePerson da hata ile karşılaşıldı.  HATA= "+e);
            System.out.println("delete person  " + e);
        }
        return sendString;
    }

    private void execute(String sql, Object... queryParameters) throws SQLException, Exception {

        PreparedStatement ps = connection.prepareStatement(sql);
        int index = 1;
        if (queryParameters != null) {

            // queryParameters.length(n-> ps.setObject(index++,n));
            for (Object paramater : queryParameters) {
                ps.setObject(index++, paramater);
            }
        }
        ps.executeUpdate();
    }

    private ResultSet executeQuery(String sql, Object... queryParameters) throws SQLException, Exception {

        PreparedStatement ps = connection.prepareStatement(sql);

        int index = 1;
        if (queryParameters != null) {
            for (Object paramater : queryParameters) {
                ps.setObject(index++, paramater);
            }
        }
        ResultSet resultSet = ps.executeQuery();
        return resultSet;
    }
}
