package com.bottle.pubs.places.response;

import com.bottle.pubs.places.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCommentsResponse {
    private int comments_count;
    private List<Comment> commentList;
    private int start_number;
    private int end_number;
}
