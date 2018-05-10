package com.bottle.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@Controller
public class MessageSubsystemClient {

    public void addUser(String token) {
        String url = "http://127.0.0.1:8083/user/add_user";
        try {
            Request.Post(url)
                    .bodyForm(Form.form()
                            .add("access_token", token)
                            .build())
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
