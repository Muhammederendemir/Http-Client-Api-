package com.dao;

import com.model.Country;
import com.model.School;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class SchoolDAOImpl implements SchoolDAO {
    private final String GET_SCHOOL_NO = "SELECT * FROM school INNER JOIN country ON school.countryId=country.countryNo WHERE school.schoolNo=?";
    private final String SAVE_SCHOOL = "INSERT INTO school(schoolNo,schoolName,countryId) VALUES(?,?,?)";
    private final String UPDATE_SCHOOL = "UPDATE school SET schoolName=?,countryId=? WHERE schoolNo=? ";
    private final String DELETE_SCHOOL = "DELETE FROM school WHERE schoolNo=?";
    private final Logger LOGGER = Logger.getLogger(SchoolDAOImpl.class);
    Connection connection;


    public SchoolDAOImpl() {
        this.connection = getConnection();
    }

    private Connection getConnection() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = getClass().getResourceAsStream("/database.properties");
            //input = ClassLoader.getSystemClassLoader().getResourceAsStream("database.properties");
            prop.load(input);
            LOGGER.info("database.properties load edilmiştir");
        } catch (IOException io) {
            LOGGER.error("database.properties bulununamadı  HATA= " + io);

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
            LOGGER.info("database bağlantısı kurulmuştur.");
            return connection = DriverManager.getConnection(JDBC_URL, properties);
        } catch (Exception e) {
            LOGGER.error("Drive manager bulunamadı HATA= " + e);
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

    public void saveSchool(School school) throws Exception {

        execute(SAVE_SCHOOL, school.getSchoolNo(), school.getSchoolName(), school.getCountryNo());
        LOGGER.info("savePerson işlemi başarılı olarak çalıştı");
        //sendString = "islem basarili";
    }

    public School getSchoolByNo(int no) {
        //List<Person> personList=null;
        try {
            ResultSet resultSet = executeQuery(GET_SCHOOL_NO, no);
            School school = new School();
            Country country = new Country();
            while (resultSet.next()) {
                //Person person = new Person();
                school.setSchoolNo(resultSet.getInt("schoolNo"));
                school.setSchoolName(resultSet.getString("schoolName"));
                school.setCountryNo(resultSet.getInt("countryNo"));

                //personList.add(person);
                LOGGER.info("schoolGetByNo işlemi başarılı olarak çalıştı");
                return school;
            }
        } catch (Exception e) {
            LOGGER.error("schoolGetByNo işlemi calışırken bir hata meydana geldi");
        }
        return null;
    }

    public void updateSchool(School school) throws Exception {
        execute(UPDATE_SCHOOL, school.getSchoolName(), school.getCountryNo(), school.getSchoolNo());
        LOGGER.info("upateSchool işlemi başarılı olarak çalıştı");
    }

    public void deleteSchoolByNo(int schoolNo) throws Exception {
        execute(DELETE_SCHOOL, schoolNo);
        LOGGER.info("deleteSchoolByNo işlemi başarılı olarak çalıştı");
    }
}
