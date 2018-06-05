package com.bottle.service.post;

import com.bottle.model.DTO.CommentDTO;
import com.bottle.model.entity.*;
import com.bottle.model.repository.*;
import com.bottle.service.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private PostRepository postRepository;
    private Builder builder;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository, Builder builder) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.builder = builder;
    }

    @Transactional
    public Comment saveComment(CommentDTO commentDTO) {
        User user = userRepository.findOne( commentDTO.getUserId() );
        Post post = postRepository.findOne( commentDTO.getPostId() );
        Comment comment = builder.buildComment( commentDTO, user, post );
        commentRepository.save( comment );
        return comment;
    }

    @Transactional
    public List<Comment> getComments(UUID postId) {
        return commentRepository.findAllByIsDeletedIsFalseAndPostId( postId );
    }

    @Transactional
    public void deleteCommentFromPost (UUID commentId) {
        commentRepository.delete( commentId );
    }

    @Transactional
    public boolean deleteComment(UUID userId, UUID commentId) {
        if (commentRepository.existsByIdAndUserId( commentId, userId )) {
            commentRepository.delete( commentId );
            return true;
        }
        return false;
    }

    @Transactional
    public Comment addComment(UUID authId, UUID postId, String text) {
        Comment comment = new Comment();
        User user = userRepository.getOne( authId );
        Post post = postRepository.findOne( postId );
        comment.setId( UUID.randomUUID() );
        comment.setUser( user );
        comment.setPost( post );
        comment.setText( text );
        comment.setIsDeleted( false );
        commentRepository.save( comment );
        return comment;
    }
}