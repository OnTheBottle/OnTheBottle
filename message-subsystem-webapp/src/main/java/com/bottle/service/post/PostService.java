package com.bottle.service.post;

import com.bottle.model.DTO.PostDTO;
import com.bottle.model.DTO.SaverDTO;
import com.bottle.model.entity.*;
import com.bottle.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class PostService {
    private PostRepository postRepository;
    private SecurityRepository securityRepository;
    private SaverRepository saverRepository;
    private UserRepository userRepository;
    private UploadFileRepository uploadFileRepository;

    @Autowired
    public PostService(PostRepository postRepository, SecurityRepository securityRepository, SaverRepository saverRepository, UserRepository userRepository, UploadFileRepository uploadFileRepository) {
        this.postRepository = postRepository;
        this.securityRepository = securityRepository;
        this.saverRepository = saverRepository;
        this.userRepository = userRepository;
        this.uploadFileRepository=uploadFileRepository;
    }

    @Transactional
    public void addPost(PostDTO postDTO) {
        Post post = new Post();
        User user = userRepository.findOne( postDTO.getUserId() );
        Security securite = securityRepository.getSecurityByDescription( postDTO.getSecurity() );
        if (post.getDate() == null)
            post.setDate( new Date() );
        post.setSecurity( securite );
        post.setTitle( postDTO.getTitle() );
        post.setUser( user );
        post.setText( postDTO.getText() );
        post.setIsDeleted( false );
        List<UploadFile> sourceUploadFiles = postDTO.getUploadFiles();
        Post postData = postRepository.save( post );
        for (UploadFile uploadFile : sourceUploadFiles) {
            uploadFile.setPost( postData );
            uploadFileRepository.save(uploadFile);
        }
    }

    @Transactional
    public List<Post> getPostsWithSaver(UUID userId) {
        boolean a = saverRepository.exists( userId );
        if (a) {
            Saver saver = saverRepository.findOne( userId );
            return postRepository.findAllByUserIdOrSaversContainingAndIsDeletedFalseOrderByDateDesc( userId, saver );
        } else {
            Saver saver = new Saver();
            saver.setId( userId );
            saverRepository.save( saver );
            return postRepository.findAllByUserIdOrSaversContainingAndIsDeletedFalseOrderByDateDesc( userId, saver );
        }
    }

    @Transactional
    public List<Post> getPostsFriend(UUID userId) {
        boolean a = userRepository.exists( userId );
        if (!a) {
            User user = new User();
            user.setId( userId );
            userRepository.save( user );
        }
        List<Post> posts1 = postRepository.findAllByIsDeletedIsFalseAndUserIdAndSecurity_Name( userId, "Group" );
        List<Post> posts2 = postRepository.findAllByIsDeletedIsFalseAndUserIdAndSecurity_Name( userId, "Public" );
        posts1.addAll( posts2 );
        return posts1;
    }

    @Transactional
    public void postToMyWall(SaverDTO saverDTO) {
        Post post = postRepository.findOne( saverDTO.getPostId() );
        Saver saver = saverRepository.findOne( saverDTO.getSaverId() );
        post.getSavers().add( saver );
        saver.getPosts().add( post );
        postRepository.save( post );
        saverRepository.save( saver );
    }

    @Transactional
    public List<Post> getPosts(UUID userId) {
        boolean a = userRepository.exists( userId );
        if (!a) {
            User user = new User();
            user.setId( userId );
            userRepository.save( user );
        }
        return postRepository.findAllByIsDeletedIsFalseAndUserId( userId );
    }

    @Transactional
    public void deleteFromWall(SaverDTO saverDTO) {
        Post post = postRepository.findOne( saverDTO.getPostId() );
        Saver saver = saverRepository.findOne( saverDTO.getSaverId() );
        post.getSavers().remove( saver );
        saver.getPosts().remove( post );
        postRepository.save( post );
        saverRepository.save( saver );
    }

    @Transactional
    public void deletePost(UUID postId) {
        Post post = postRepository.findOne( postId );
        post.setIsDeleted( true );
        postRepository.save( post );
    }

    @Transactional
    public void updatePost(PostDTO postDTO) {
        Post post = postRepository.findOne( postDTO.getId() );
        Security security = securityRepository.getSecurityByDescription( postDTO.getSecurity() );
        post.setTitle( postDTO.getTitle() );
        post.setText( postDTO.getText() );
        post.setSecurity( security );
        post.setId( postDTO.getId() );
        postRepository.save( post );
    }
}
