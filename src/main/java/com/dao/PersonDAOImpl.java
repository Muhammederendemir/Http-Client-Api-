package com.dao;

import com.model.Person;
import org.json.JSONObject;

import java.sql.*;


public class PersonDAOImpl implements PersonDAO {
    private final String GET_PERSON_ID = "SELECT * FROM person Where personId=?";
    private final String SAVE_PERSON = "INSERT INTO person(personId,personName,personSurname) VALUES(?,?,?)";
    private final String UPDATE_PERSON = "UPDATE person SET personName=?,personSurname=? WHERE personId=? ";
    private final String DELETE_PERSON = "  DELETE FROM person WHERE personId=?";
    Person person = new Person();
    Connection connection;

    public PersonDAOImpl() {
        this.connection = getConnection();
    }

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/javaexample";
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("\n sql e baglandi");
        } catch (Exception ex) {
            System.out.println("Cannot connect to database server");
            ex.printStackTrace();
        }
        return connection;
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
            System.out.println(e);
        }

        return sendString;
    }

    /* public String getPersonById(JSONObject jsonObject) {
         String insertdata = jsonObject.toString();
         parseId(insertdata);
         JSONArray jsonArray = null;
         try
         {
             //statement=connection.prepareStatement(selectRc);
             ResultSet resultSet = executeQuery(GET_PERSON_ID, person.getId());
             while(resultSet.next())
             {
                 JSONObject jsonObjectOut = new JSONObject();
                 int id=resultSet.getInt("personId");
                 String name=resultSet.getString("personName");
                 String surname=resultSet.getString("personSurname");
                 jsonObjectOut.put("id", id);
                 jsonObjectOut.put("name", name);
                 jsonObjectOut.put("surname", surname);

                 jsonArray.put(jsonObjectOut);//hata burada maven de
             }
         }catch(Exception e){
             System.out.println(e);
         }
         String getdata = jsonArray.toString();
         return getdata;
     }
 */
    private void execute(String sql, Object... queryParameters) throws Exception {

        PreparedStatement ps = connection.prepareStatement(SAVE_PERSON);
        int index = 1;
        if (queryParameters != null) {

            // queryParameters.length(n-> ps.setObject(index++,n));
            for (Object paramater : queryParameters) {
                ps.setObject(index++, paramater);
            }
        }
        ps.execute();
    }

    private ResultSet executeQuery(String sql, Object... queryParameters) throws SQLException {

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
