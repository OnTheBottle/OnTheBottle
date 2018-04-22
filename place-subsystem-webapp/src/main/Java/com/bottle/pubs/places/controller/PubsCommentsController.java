package com.bottle.pubs.places.controller;

import com.bottle.pubs.places.entity.Comment;
import com.bottle.pubs.places.repository.PubsCommentsRepository;
import com.bottle.pubs.places.request.AddCommentsRequest;
import com.bottle.pubs.places.request.GetCommentsRequest;
import com.bottle.pubs.places.response.AddCommentsResponse;
import com.bottle.pubs.places.response.GetCommentsResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PubsCommentsController {
    private PubsCommentsRepository pubsCommentsRepository;

    @Autowired
    public PubsCommentsController(PubsCommentsRepository pubsCommentsRepository) {
        this.pubsCommentsRepository = pubsCommentsRepository;
    }

    @GetMapping(path = "/getPubComments")
    @ResponseBody
    public GetCommentsResponse getPubs(GetCommentsRequest request) {
        List<Comment> allComments = pubsCommentsRepository.getCommentsByPlace_id(request.getPlace_id());
        final int start = request.getStart_number();
        final int end = request.getEnd_number();
        GetCommentsResponse response = new GetCommentsResponse();
        response.setStart_number(start);
        response.setEnd_number(end);
        response.setComments_count(allComments.size());
        //response.setCommentList(sliceList(allComments, start, end));

        return response;
    }

    /*private List<Comment> sliceList(List<Comment> allComments, int start, int end) {
        if (end > allComments.size()) {
            end = allComments.size();
        }
        List<Comment> result = new ArrayList<>();
        for (; start <= end; start++) {
            result.add(allComments.get(start));
        }
        return result;
    }*/

    @PostMapping(path = "/addPubComment")
    @ResponseBody
    public AddCommentsResponse getPubs(AddCommentsRequest request) {
        AddCommentsResponse response = new AddCommentsResponse();
        Comment newComment = new Comment();
        newComment.setComment_text(request.getComment_text());
        newComment.setUser_id(request.getUser_id());
        newComment.setPlace_id(request.getPub_id());
        Comment dbResult = pubsCommentsRepository.save(newComment);
        response.setResult(dbResult != null);
        return response;
    }
}
