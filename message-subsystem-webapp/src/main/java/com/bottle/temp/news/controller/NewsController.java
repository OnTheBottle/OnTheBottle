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

    @RequestMapping(path = "/getnews", method = RequestMethod.POST)
    public List<Post> getFriends(@RequestParam(name = "id") String id){
        UUID uuid = UUID.fromString(id);
        List<Map> friends = client.getFriends(uuid);
        List<Post> posts = new ArrayList<>();
        for (Map<String,String> friend: friends){
            UUID friendId = UUID.fromString(friend.get("id"));
            posts.addAll(postRepository.getPostByAuthorId(friendId));
        }
        return posts;
    }


    @GetMapping(path = "/printrequest")
    public void getAllParams(@RequestParam Map<String, String> allRequestParams) {
        System.out.println(allRequestParams);
    }

}
