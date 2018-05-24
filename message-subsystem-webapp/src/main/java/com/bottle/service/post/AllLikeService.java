package com.bottle.service.post;

import com.bottle.model.DTO.LikeDTO;
import com.bottle.model.entity.Like;
import com.bottle.model.entity.Post;
import com.bottle.model.entity.User;
import com.bottle.service.user.AllUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AllLikeService {
    private LikeService likeService;
    private AllUserService allUserService;
    private PostService postService;

    @Autowired
    public AllLikeService(LikeService likeService, PostService postService, AllUserService allUserService) {
        this.likeService = likeService;
        this.allUserService = allUserService;
        this.postService = postService;
    }

    public boolean addLike(LikeDTO likeDTO) {
        Post post = postService.getPost( likeDTO.getPost_id() );
        User user = allUserService.getUser( likeDTO.getUser_id() );
        boolean proverka = likeService.exists( likeDTO.getPost_id(), likeDTO.getUser_id() );
        if (proverka) {
            return true;
        } else {
            Like like = new Like( likeDTO.getStatus(), post, user );
            likeService.save( like );
            return false;
        }
    }

    public List<Like> getLikes(UUID postId) {
        return likeService.findByPostId( postId );
    }
}