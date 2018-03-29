package com.bottle.service.post;

import com.bottle.model.entity.Comment;
import com.bottle.model.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;


@Service
public class CommentService {

    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional
    public Iterable<Comment> getComments(UUID post_id) {
        return commentRepository.findByPostId( post_id );
    }

    @Transactional
    public void addComment(Comment comment) {
        commentRepository.save( comment );
    }

    @Transactional
    public void deleteCommentById(UUID comment_id) {
        commentRepository.delete( comment_id );
    }
}
