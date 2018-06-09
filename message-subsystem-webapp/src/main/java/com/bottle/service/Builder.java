package com.bottle.service;

import com.bottle.model.DTO.CommentDTO;
import com.bottle.model.DTO.PostDTO;
import com.bottle.model.DTO.Request.EventDTO;
import com.bottle.model.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class Builder {
    public Event buildEvent(EventDTO eventDTO) {
        Event event = new Event();
        String title = eventDTO.getTitle();
        String text = eventDTO.getText();
        Date startTime = eventDTO.getStartTime();
        Date endTime = eventDTO.getEndTime();

        event.setTitle(title);
        event.setText(text);
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        event.setUsersCounter(0);
        event.setIsActive(true);
        event.setId(UUID.randomUUID());

        return event;
    }

    public Post buildPost(PostDTO postDTO, Set<UploadFile> files, User user, Security security) {
        Post post = new Post();
        if (post.getDate() == null)
            post.setDate(new Date());
        post.setId(UUID.randomUUID());
        post.setSecurity(security);
        post.setTitle(postDTO.getTitle());
        post.setUser(user);
        post.setText(postDTO.getText());
        post.setIsDeleted(false);
        if (post.getComments() == null) {
            post.setComments(new HashSet<>());
        } else {
            post.setComments(postDTO.getComments());
        }
        if (post.getLikes() == null) {
            post.setLikes(new HashSet<>());
        } else {
            post.setLikes(postDTO.getLikes());
        }
        post.setUploadFiles(files);
        return post;
    }

    public Comment buildComment(CommentDTO commentDTO, User user, Post post) {
        Comment comment = new Comment();
        if (comment.getDate() == null)
            comment.setDate(new Date());
        comment.setId(UUID.randomUUID());
        comment.setText(commentDTO.getComment());
        comment.setPost(post);
        comment.setUser(user);
        comment.setIsDeleted(false);
        return comment;
    }

    public UploadFile buildFileInfo(MultipartFile multipartFile, String path, String location) {
        UploadFile fileInfo = new UploadFile();
        fileInfo.setId(UUID.randomUUID());
        fileInfo.setName(multipartFile.getOriginalFilename());
        fileInfo.setSize(multipartFile.getSize());
        fileInfo.setType(multipartFile.getContentType());
        fileInfo.setUrl(path);
        fileInfo.setLocation(location);
        return fileInfo;
    }


}
