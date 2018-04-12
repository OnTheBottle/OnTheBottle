package com.bottle.temp.news.controller;

import com.bottle.temp.news.client.NewsClient;
import com.bottle.temp.news.entity.LikePost;
import com.bottle.temp.news.entity.Post;
import com.bottle.temp.news.repository.LikePostRepository;
import com.bottle.temp.news.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/news")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NewsController {
    @Autowired
    NewsClient client;
    @Autowired
    PostRepository postRepository;
    LikePostRepository likeRepository;

    //@CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(path = "/getfriendsposts", method = RequestMethod.POST)
    public List<List> getFriendsPosts(@RequestParam(name = "id") String id) {
        System.out.println("request id: " + id);
        UUID uuid = UUID.fromString(id);
        List<Map> friends = client.getFriends(uuid);
        System.out.println(friends);
        List<Post> posts = new ArrayList<>();
        for (Map<String, String> friend : friends) {
            UUID friendId = UUID.fromString(friend.get("id"));
            posts.addAll(postRepository.getPostByAuthorId(friendId));
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
        List<LikePost> likePosts = likeRepository.getLikePostByPostId(postId);
        System.out.println(likePosts);
    }

    @RequestMapping(path = "/addlike", method = RequestMethod.POST)
    public void addLike() {
        UUID userId;
        UUID postId;
        Date date;

        userId = UUID.fromString("36fe8f70-3287-4521-b00f-807682ab8204");
        postId = UUID.fromString("bfae92d5-84c1-46ec-afe4-57a44bfac85e");
        date = new Date();

        LikePost like = new LikePost();
        like.setUserId(userId);
        like.setPostId(postId);
        like.setDate(date);
        System.out.println(like);
        likeRepository.save(like);
    }

    @GetMapping(path = "/printrequest")
    public void getAllParams(@RequestParam Map<String, String> allRequestParams) {
        System.out.println(allRequestParams);
    }

}
