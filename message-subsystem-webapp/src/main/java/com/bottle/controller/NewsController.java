package com.bottle.controller;

import com.bottle.client.UserSubsystemClient;
import com.bottle.model.entity.Like;
import com.bottle.model.entity.Post;
import com.bottle.model.entity.User;
import com.bottle.model.repository.LikeRepository;
import com.bottle.model.repository.PostRepository;
import com.bottle.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/news")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NewsController {
    private final UserSubsystemClient client;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;

    @Autowired
    public NewsController(UserSubsystemClient client, PostRepository postRepository, LikeRepository likeRepository, UserRepository userRepository) {
        this.client = client;
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(path = "/get_friends_posts", method = RequestMethod.POST)
    public List<List> getFriendsPosts(@RequestParam(name = "id") UUID userId) {
        System.out.println("request id: " + userId);
        List<Map> friends = client.getFriends(userId);
        System.out.println(friends);
        List<Post> posts = new ArrayList<>();
        for (Map<String, String> friend : friends) {
            UUID friendId = UUID.fromString(friend.get("user_id"));
            posts.addAll(postRepository.findAllByUserId(friendId));
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
