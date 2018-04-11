package com.bottle.temp.news.controller;

import com.bottle.temp.news.client.NewsClient;
import com.bottle.temp.news.entity.Post;
import com.bottle.temp.news.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/news")
public class NewsController {
    @Autowired
    NewsClient client;
    @Autowired
    PostRepository postRepository;

    @CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(path = "/getfriendsposts", method = RequestMethod.POST)
    public List<List> getFriendsPosts(@RequestParam(name = "id") String id) {
        System.out.println("request id: " + id);
        UUID uuid = UUID.fromString(id);
        List<Map> friends = client.getFriends(uuid);
        List<Post> posts = new ArrayList<>();
        for (Map<String, String> friend : friends) {
            UUID friendId = UUID.fromString(friend.get("id"));
            posts.addAll(postRepository.getPostByAuthorId(friendId));
        }
        //System.out.println(posts);
        List<List> resp = new ArrayList<List>() {{
            add(friends);
            add(posts);
        }};
        return resp;
    }


    @GetMapping(path = "/printrequest")
    public void getAllParams(@RequestParam Map<String, String> allRequestParams) {
        System.out.println(allRequestParams);
    }

}
