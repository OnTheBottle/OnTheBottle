package com.bottle.service.post;

import com.bottle.model.DTO.CommentDTO;
import com.bottle.model.entity.*;
import com.bottle.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private PostRepository postRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public void saveComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        User user = userRepository.findOne( commentDTO.getUserId() );
        Post post = postRepository.findOne( commentDTO.getPostId() );
        if (comment.getDate() == null)
            comment.setDate( new Date() );
        comment.setId( UUID.randomUUID() );
        comment.setText( commentDTO.getComment() );
        comment.setPost( post );
        comment.setUser( user );
        comment.setIsDeleted( false );
        commentRepository.save( comment );
    }

    @Transactional
    public List<Comment> getComments(UUID postId) {
        return commentRepository.findAllByIsDeletedIsFalseAndPostId( postId );
    }

    @Transactional
    public void deleteComment(UUID commentId) {
        Comment comment = commentRepository.findOne( commentId );
        comment.setIsDeleted( true );
        commentRepository.save( comment );
    }

    @Transactional
    public boolean isDeletedComment(UUID userId, UUID commentId) {
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