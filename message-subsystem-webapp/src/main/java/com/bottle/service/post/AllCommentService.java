package com.bottle.service.post;

import com.bottle.model.DTO.CommentDTO;
import com.bottle.model.entity.Comment;
import com.bottle.model.entity.Post;
import com.bottle.model.entity.User;
import com.bottle.service.user.AllUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class AllCommentService {
    private CommentService commentService;
    private AllUserService allUserService;
    private PostService postService;

    @Autowired
    public AllCommentService(CommentService commentService,PostService postService,AllUserService allUserService){
        this.commentService=commentService;
        this.allUserService=allUserService;
        this.postService=postService;
    }

    public void saveComment(CommentDTO commentDTO){
        Comment comment = new Comment();
        User user = allUserService.getUser( commentDTO.getUser_id() );
        Post post = postService.getPost( commentDTO.getPost_id() );
        if (comment.getDate() == null)
            comment.setDate( new Date() );
        comment.setId( UUID.randomUUID() );
        comment.setText( commentDTO.getComment() );
        comment.setPost( post );
        comment.setUser( user );
        comment.setIsDeleted( false );
        commentService.addComment( comment );
    }

    public List<Comment> getComments(UUID postId){
        List<Comment> comments = commentService.getComments( postId );
            return comments;
        }

    public void deleteComment(UUID commentId){
        Comment comment = commentService.getCommentById( commentId );
        comment.setIsDeleted( true );
        commentService.addComment( comment );
    }
    }