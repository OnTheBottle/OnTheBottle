package com.bottle.controller;

import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class PlaceSubsystemClient {
    @Value("${sub.message.path}")
    private String subMessagePath;

    public void addPlace(String token, String id) {
        String url = subMessagePath + "/place/addPlace";
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
