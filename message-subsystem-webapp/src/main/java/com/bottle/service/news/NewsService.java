package com.bottle.service.news;

import com.bottle.client.UserSubsystemClient;
import com.bottle.model.entity.Post;
import com.bottle.model.repository.LikeRepository;
import com.bottle.model.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class NewsService {

    private UserSubsystemClient client;
    private PostRepository postRepository;
    private LikeRepository likeRepository;

    @Autowired
    public NewsService(UserSubsystemClient client, PostRepository postRepository, LikeRepository likeRepository) {
        this.client = client;
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
    }

    public List getFriendsPosts(UUID userId, String token) {
        List<Map> friends = client.getFriends(userId, token);
        List<Post> posts = new ArrayList<>();
        for (Map<String, String> friend : friends) {
            UUID friendId = UUID.fromString(friend.get("id"));
            int securityLevel = 2; // 1 - view All, 2 - view only Friends, 3 - view only Owner
            posts.addAll(postRepository.findAllByIsDeletedIsFalseAndUserIdAndSecurityIdLessThan(friendId, securityLevel + 1));
        }
        List<List> resp = new ArrayList<List>() {{
            add(friends);
            add(posts);
        }};
        System.out.println("getFriendsPosts All response data: \n" + resp);
        return resp;
    }

    public List getUserPosts(UUID userId, String token) {
        List<Map> friends = new ArrayList<>();
        List<Post> posts = new ArrayList<>();
        friends.add(client.getFriend(userId, token));

        int securityLevel = 1;
        if (client.isFriend(userId, token)) securityLevel = 2;

        posts.addAll(postRepository.findAllByIsDeletedIsFalseAndUserIdAndSecurityIdLessThan(userId, securityLevel + 1));

        List<List> resp = new ArrayList<List>() {{
            add(friends);
            add(posts);
        }};

        System.out.println("getUserPosts All response data: \n" + resp);
        return resp;
    }
}
