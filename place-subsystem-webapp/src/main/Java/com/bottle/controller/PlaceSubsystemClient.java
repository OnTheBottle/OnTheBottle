package com.bottle.controller;

import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.UUID;

@Controller
public class PlaceSubsystemClient {
    public void addPlace(String token, String id) {
        String url = "http://127.0.0.1:8083/place/addPlace";
        try {
            Request.Post(url)
                    .bodyForm(Form.form()
                            .add("access_token", token)
                            .add("place", id)
                            .build())
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
