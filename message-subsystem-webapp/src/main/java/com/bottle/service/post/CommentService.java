package com.bottle.service.post;

import com.bottle.model.entity.Comment;
import com.bottle.model.entity.Post;
import com.bottle.model.entity.User;
import com.bottle.model.repository.CommentRepository;
import com.bottle.model.repository.PostRepository;
import com.bottle.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;


@Service
public class CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;


    @Autowired
    public CommentService(PostRepository postRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Transactional
    public List<Comment> getComments(UUID post_id) {
        return commentRepository.findAllByIsDeletedIsFalseAndPostId(post_id);
    }

    @Transactional
    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Transactional
    public Comment addComment(UUID authId, UUID postId, String text) {
        Comment comment = new Comment();
        User user = userRepository.getOne(authId);
        Post post = postRepository.getById(postId);
        comment.setId(UUID.randomUUID());
        comment.setUser(user);
        comment.setPost(post);
        comment.setText(text);
        comment.setIsDeleted(false);
        commentRepository.save(comment);
        return comment;
    }

    @Transactional
    public void deleteCommentById(UUID comment_id) {
        commentRepository.delete(comment_id);
    }

    @Transactional
    public Comment getCommentById(UUID comment_id) {
        return commentRepository.findOne(comment_id);
    }

}
