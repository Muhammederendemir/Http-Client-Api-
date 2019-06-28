package com;

import org.json.JSONObject;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class Test {

    public static void main(String[] args) {
        String uri = "http://localhost:8090/msg/personService/personCrud?personId=5";

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(uri);
        // target.queryParam("personId","5");

        Invocation.Builder builder = target.request();
        Response message = builder.get();

        String output = message.readEntity(String.class);

        JSONObject jsonObject = new JSONObject(output);

        System.out.println(jsonObject.get("id").toString());
        System.out.println(jsonObject.get("name").toString());
        System.out.println(jsonObject.get("surname").toString());

        client.close();


    }
}

