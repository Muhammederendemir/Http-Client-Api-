package com.dao;

import com.model.Person;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;


public class PersonDAOImpl implements PersonDAO {
    private final String GET_PERSON_ID = "SELECT * FROM person WHERE personId=?";
    private final String SAVE_PERSON = "INSERT INTO person(personId,personName,personSurname) VALUES(?,?,?)";
    private final String UPDATE_PERSON = "UPDATE person SET personName=?,personSurname=? WHERE personId=? ";
    private final String DELETE_PERSON = "DELETE FROM person WHERE personId=?";
    Connection connection;
    Logger logger = Logger.getLogger(PersonDAO.class);


    public PersonDAOImpl() {
        this.connection = getConnection();
    }

    private Connection getConnection() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = getClass().getResourceAsStream("/database.properties");
            //input = ClassLoader.getSystemClassLoader().getResourceAsStream("database.properties");
            prop.load(input);
            logger.info("database.properties load edilmiştir");
        } catch (IOException io) {
            logger.error("database.properties bulununamadı  HATA= " + io);

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
            logger.info("database bağlantısı kurulmuştur.");
            return connection = DriverManager.getConnection(JDBC_URL, properties);
        } catch (Exception e) {
            logger.error("Drive manager bulunamadı HATA= " + e);
            //System.out.println("Error : " + e);
        }
        return null;
    }

    private void execute(String sql, Object... queryParameters) throws Exception {

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

    public void savePerson(Person person) throws Exception {

            execute(SAVE_PERSON, person.getId(), person.getName(), person.getSurname());
        logger.info("savePerson işlemi başarılı olarak çalıştı");
        //sendString = "islem basarili";
    }

    public Person getPersonById(int id) throws Exception {
            //statement=connection.prepareStatement(selectRc);
            //List<Person> personList=null;
            ResultSet resultSet = executeQuery(GET_PERSON_ID, id);
            Person person = new Person();
            while (resultSet.next()) {
                //Person person = new Person();
                person.setId(resultSet.getInt("personId"));
                person.setName(resultSet.getString("personName"));
                person.setSurname(resultSet.getString("personSurname"));
                //personList.add(person);

            }
        logger.info("personGetById işlemi başarılı olarak çalıştı");
            return person;
    }

    public void updatePerson(Person person) throws Exception {
        execute(UPDATE_PERSON, person.getName(), person.getSurname(), person.getId());
        logger.info("upatePerson işlemi başarılı olarak çalıştı");
    }

    public void deletePersonById(int id) throws Exception {
            execute(DELETE_PERSON, id);
        logger.info("deletePerson işlemi başarılı olarak çalıştı");
            //loglama olacak
    }
}
