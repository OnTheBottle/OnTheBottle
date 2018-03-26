package com.bottle.userWall.home.service;
import com.home.entity.Comment;
import com.home.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;


@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Transactional
    public Iterable<Comment> getComments(UUID post_id) {
        return commentRepository.findByPostId(post_id);
    }
    @Transactional
    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }
    @Transactional
    public void deleteCommentById (UUID comment_id){
        commentRepository.delete(comment_id);
    }
}
