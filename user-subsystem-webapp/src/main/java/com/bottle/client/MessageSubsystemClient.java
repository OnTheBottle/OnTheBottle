package com.bottle.client;

import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class MessageSubsystemClient {
    @Value("${sub.message.path}")
    private String subMessagePath;

    public void addUser(String token) {
        String url = subMessagePath + "/user/add_user";
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
