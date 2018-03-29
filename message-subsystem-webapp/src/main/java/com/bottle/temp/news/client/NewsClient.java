package com.bottle.temp.news.client;

import com.bottle.temp.news.dto.FriendDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class NewsClient {

    public void getFriendsTest(UUID id) {
        String remoteHost = "http://127.0.0.1:8080/news/printrequest?id=" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(remoteHost, String.class);
        System.out.println(response);
    }

    public List getFriends(UUID id) {
        String url = "http://127.0.0.1:8080/user/getfriends?id=" + id;
        //POST request
        return new RestTemplate().postForObject(url, null, ArrayList.class);
    }
}
