package com.bottle.temp.news.controller;

import com.bottle.temp.news.client.NewsClient;
import com.bottle.temp.news.entity.LikePost;
import com.bottle.temp.news.entity.Post;
import com.bottle.temp.news.repository.LikePostRepository;
import com.bottle.temp.news.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/news")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NewsController {
    private NewsClient client;
    private PostRepository repository;

    @Autowired
    public NewsController(NewsClient client, PostRepository repository) {
        this.client = client;
        this.repository = repository;
    }

    //@CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(path = "/getfriendsposts", method = RequestMethod.POST)
    public List<List> getFriendsPosts(@RequestParam(name = "id") UUID id) {
        System.out.println("request id: " + id);
        //UUID uuid = UUID.fromString(id);
        List<Map> friends = client.getFriends(id);
        System.out.println(friends);
        List<Post> posts = new ArrayList<>();
        for (Map<String, String> friend : friends) {
            UUID friendId = UUID.fromString(friend.get("id"));
            posts.addAll(repository.getPostByAuthorId(friendId));
        }
        List<List> resp = new ArrayList<List>() {{
            add(friends);
            add(posts);
        }};
        System.out.println("All resp data: \n" + resp);
        return resp;
    }

    @GetMapping(path = "/printrequest")
    public void getAllParams(@RequestParam Map<String, String> allRequestParams) {
        System.out.println(allRequestParams);
    }

}
