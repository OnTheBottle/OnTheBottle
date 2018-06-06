package com.bottle.service.post;

import com.bottle.model.DTO.PostDTO;
import com.bottle.model.entity.*;
import com.bottle.model.repository.*;
import com.bottle.service.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class PostService {
    private PostRepository postRepository;
    private SecurityRepository securityRepository;
    private SaverRepository saverRepository;
    private UserRepository userRepository;
    private UploadFileService uploadFileService;
    private Builder builder;

    @Value("${pagination.limit}")
    private int limit;

    @Autowired
    public PostService(PostRepository postRepository, SecurityRepository securityRepository, SaverRepository saverRepository, UserRepository userRepository, UploadFileService uploadFileService, Builder builder) {
        this.postRepository = postRepository;
        this.securityRepository = securityRepository;
        this.saverRepository = saverRepository;
        this.userRepository = userRepository;
        this.uploadFileService = uploadFileService;
        this.builder = builder;
    }

    @Transactional
    public Post addPost(PostDTO postDTO) {
        User user = userRepository.findOne( postDTO.getUserId() );
        Security security = securityRepository.getSecurityByDescription( postDTO.getSecurity() );
        Set<UploadFile> uploadFiles = getSetUploadFiles( postDTO.getUploadFiles() );
        Post post = builder.buildPost( postDTO, uploadFiles, user, security );
        postRepository.save( post );
        uploadFileService.linkFilesWithPost( postDTO.getUploadFiles(), post );
        return post;
    }

    @Transactional
    public List<Post> getPostsWithSaver(UUID userId) {
        boolean a = saverRepository.exists( userId );
        if (a) {
            Saver saver = saverRepository.findOne( userId );
            return postRepository.findFirst5ByIsDeletedIsFalseAndUserIdOrSaversContainingOrderByDateDesc( userId, saver );
        } else {
            Saver saver = new Saver();
            saver.setId( userId );
            saverRepository.save( saver );
            return postRepository.findFirst5ByIsDeletedIsFalseAndUserIdOrSaversContainingOrderByDateDesc( userId, saver );
        }
    }

    @Transactional
    public List<Post> getMorePosts(UUID userId, UUID lastPostId) {
        List<Post> posts;
        Saver saver = saverRepository.findOne( userId );
        List<Post> morePosts = postRepository.findAllByIsDeletedIsFalseAndUserIdOrSaversContainingOrderByDateDesc( userId, saver );
        Post post = postRepository.findOne( lastPostId );
        int indexRow = morePosts.indexOf( post );
        System.out.println( indexRow );
        System.out.println( "размер листа" + morePosts.size() );
        if (morePosts.size() <= indexRow + limit+1) {
            posts = morePosts.subList( indexRow + 1, morePosts.size());
        } else {
            posts = morePosts.subList( indexRow + 1, indexRow + limit+1 );
        }
        return posts;
    }

    @Transactional
    public List<Post> getPostsFriend(UUID userId) {
        boolean a = userRepository.exists( userId );
        if (!a) {
            User user = new User();
            user.setId( userId );
            userRepository.save( user );
        }
        List<Post> posts1 = postRepository.findAllByIsDeletedIsFalseAndUserIdAndSecurityName( userId, "Group" );
        List<Post> posts2 = postRepository.findAllByIsDeletedIsFalseAndUserIdAndSecurityName( userId, "Public" );
        posts1.addAll( posts2 );
        return posts1;
    }

    @Transactional
    public void postToMyWall(UUID postId, UUID saverId) {
        Post post = postRepository.findOne( postId );
        Saver saver = saverRepository.findOne( saverId );
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
    public void deleteFromWall(UUID postId, UUID saverId) {
        Post post = postRepository.findOne( postId );
        Saver saver = saverRepository.findOne( saverId );
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

    private Set<UploadFile> getSetUploadFiles(List<UploadFile> files) {
        if (!files.isEmpty()) {
            return new HashSet<>( files );
        } else {
            return new HashSet<>();
        }
    }
}
