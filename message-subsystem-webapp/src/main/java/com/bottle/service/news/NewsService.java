package com.bottle.service.news;

import com.bottle.client.UserSubsystemClient;
import com.bottle.model.entity.Post;
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

    @Autowired
    public NewsService(UserSubsystemClient client, PostRepository postRepository) {
        this.client = client;
        this.postRepository = postRepository;
    }

    public List getFriendsPosts(UUID userId){
        System.out.println("getFriendsPosts get id: " + userId);
        List<Map> friends = client.getFriends(userId);
        System.out.println("getFriendsPosts get friends: " + friends);
        List<Post> posts = new ArrayList<>();
        for (Map<String, String> friend : friends) {
            UUID friendId = UUID.fromString(friend.get("id"));
            posts.addAll(postRepository.findAllByUserId(friendId));
        }
        List<List> resp = new ArrayList<List>() {{
            add(friends);
            add(posts);
        }};
        System.out.println("getFriendsPosts All response data: \n" + resp);
        return resp;
    }
}
