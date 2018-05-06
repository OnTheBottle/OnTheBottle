package com.bottle.service.post;

import com.bottle.model.entity.Post;
import com.bottle.model.entity.User;
import com.bottle.model.repository.UserRepository;
import com.bottle.service.user.AllUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GetterPost {
    private PostService postService;
    private AllUserService allUserService;
    private UserRepository userRepository;

    @Autowired
    public GetterPost(PostService postService,AllUserService allUserService,UserRepository userRepository) {
        this.postService = postService;
        this.allUserService=allUserService;
        this.userRepository=userRepository;
    }

   public List<Post> getPosts(UUID userId){
        boolean a=userRepository.exists( userId );
        if (a){return postService.getPosts(userId);
        }
        else{User user = new User();
            user.setId( userId );
            allUserService.createUser( user );
            return postService.getPosts( userId );}
   }
}
