package com.bottle.client;

import com.bottle.Properties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class UserSubsystemClient {

    public List getFriends(UUID id) {
        String userSystemPath = Properties.SUB_USER_PATH + "/friend/get_friends_by_userid";
        String url = userSystemPath + "?id=" + id;
        System.out.println("client sends reguest: " + url);
        //POST request
        return new RestTemplate().postForObject(url, null, ArrayList.class);
    }
}
