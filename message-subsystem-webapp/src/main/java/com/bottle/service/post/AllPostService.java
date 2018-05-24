package com.bottle.service.post;

import com.bottle.model.DTO.PostDTO;
import com.bottle.model.DTO.SaverDTO;
import com.bottle.model.entity.Post;
import com.bottle.model.entity.Saver;
import com.bottle.model.entity.Security;
import com.bottle.model.entity.User;
import com.bottle.model.repository.SaverRepository;
import com.bottle.model.repository.UserRepository;
import com.bottle.service.user.AllUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class AllPostService {
    private PostService postService;
    private AllUserService allUserService;
    private SecurityService securityService;
    private SaverRepository saverRepository;
    private SaverServise saverServise;
    private UserRepository userRepository;


    @Autowired
    public AllPostService(PostService postService, AllUserService allUserService, SecurityService securityService, SaverRepository saverRepository, SaverServise saverServise, UserRepository userRepository) {
        this.postService = postService;
        this.allUserService = allUserService;
        this.securityService = securityService;
        this.saverRepository = saverRepository;
        this.saverServise = saverServise;
        this.userRepository = userRepository;
    }

    public void addPost(PostDTO postDTO) {
        Post post = new Post();
        User user = allUserService.getUser( postDTO.getUser_id() );
        Security security = securityService.getSecurityByDescription( postDTO.getSecurity() );
        if (post.getDate() == null)
            post.setDate( new Date() );
        post.setSecurity( security );
        post.setTitle( postDTO.getTitle() );
        post.setUser( user );
        post.setText( postDTO.getText() );
        post.setIsDeleted( false );
        postService.addPost( post );
    }

    public List<Post> getPostsWithSaver(UUID userId) {

        boolean a = saverRepository.exists( userId );
        if (a) {
            Saver saver = saverServise.getSaver( userId );
            return postService.getPostsForWall( userId, saver );
        } else {
            Saver saver = new Saver();
            saver.setId( userId );
            saverServise.create( saver );
            return postService.getPostsForWall( userId, saver );
        }
    }

    public List<Post> getPostsFriend(UUID userId) {
        boolean a = userRepository.exists( userId );
        if (!a) {
            User user = new User();
            user.setId( userId );
            allUserService.createUser( user );
        }
        List<Post> posts1 = postService.getPostsFriend( userId, "Group" );
        List<Post> posts2 = postService.getPostsFriend( userId, "Public" );
        posts1.addAll( posts2 );
        return posts1;
    }

    public void postToMyWall(SaverDTO saverDTO) {
        Post post = postService.getPost( saverDTO.getPostId() );
        Saver saver = saverServise.getSaver( saverDTO.getSaverId() );
        post.getSavers().add( saver );
        saver.getPosts().add( post );
        postService.addPost( post );
        saverServise.create( saver );
    }

    public List<Post> getPosts(UUID userId) {
        boolean a = userRepository.exists( userId );
        if (!a) {
            User user = new User();
            user.setId( userId );
            allUserService.createUser( user );
        }
        return postService.getPosts( userId );
    }

    public void deleteFromWall(SaverDTO saverDTO) {
        Post post = postService.getPost( saverDTO.getPostId() );
        Saver saver = saverServise.getSaver( saverDTO.getSaverId() );
        post.getSavers().remove( saver );
        saver.getPosts().remove( post );
        postService.updatePost( post );
        saverServise.update( saver );
    }

    public void deletePost(UUID postId) {
        Post post = postService.getPost( postId );
        post.setIsDeleted( true );
        postService.updatePost( post );
    }

    public void updatePost(PostDTO postDTO) {
        Post post = postService.getPost( postDTO.getId() );
        Security security = securityService.getSecurityByDescription( postDTO.getSecurity() );
        post.setTitle( postDTO.getTitle() );
        post.setText( postDTO.getText() );
        post.setSecurity( security );
        post.setId( postDTO.getId() );
        postService.addPost( post );
    }

}
