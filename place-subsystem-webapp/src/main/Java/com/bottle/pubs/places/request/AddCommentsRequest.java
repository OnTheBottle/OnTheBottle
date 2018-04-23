package com.bottle.pubs.places.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddCommentsRequest {
    private UUID user_id;
    private UUID place_id;
    private String comment_text;
    private float rating;
}
