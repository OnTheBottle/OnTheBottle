package com.bottle.controller;

import com.bottle.client.NewsClient;
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
    private final NewsClient client;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;

    @Autowired
    public NewsController(NewsClient client, PostRepository postRepository, LikeRepository likeRepository, UserRepository userRepository) {
        this.client = client;
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
    }

    //@CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(path = "/getfriendsposts", method = RequestMethod.POST)
    public List<List> getFriendsPosts(@RequestParam(name = "id") String id) {
        System.out.println("request id: " + id);
        UUID uuid = UUID.fromString(id);
        List<Map> friends = client.getFriends(uuid);
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

    @RequestMapping(path = "/setlike", method = RequestMethod.POST)
    public void getLikePost(
            @RequestParam(name = "userId") UUID userId,
            @RequestParam(name = "postId") UUID postId,
            @RequestParam(name = "isLike") boolean isLike
    ) {
        //System.out.println(postId.getClass().getName());
        System.out.println("request: " + userId + " " + postId + " " + isLike);
        Set<Like> likePosts = likeRepository.findByPost_Id(postId);
        System.out.println(likePosts);
    }

    @RequestMapping(path = "/addlike", method = RequestMethod.POST)
    public void addLike() {
        UUID userId;
        UUID postId;

        userId = UUID.fromString("36fe8f70-3287-4521-b00f-807682ab8204"); //TODO
        postId = UUID.fromString("bfae92d5-84c1-46ec-afe4-57a44bfac85e"); //TODO

        Post post = postRepository.getOne(postId);
        User user = userRepository.getOne(userId);

        Like like = new Like();
        like.setUser(user);
        like.setPost(post);
        System.out.println(like);
        likeRepository.save(like);
    }

    @GetMapping(path = "/printrequest")
    public void getAllParams(@RequestParam Map<String, String> allRequestParams) {
        System.out.println(allRequestParams);
    }

}
