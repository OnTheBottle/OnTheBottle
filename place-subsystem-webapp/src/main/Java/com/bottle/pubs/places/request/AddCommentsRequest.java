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
    // TODO: 24.04.2018 wtf?Use camelCase
    private UUID user_id;
    private UUID place_id;
    private String comment_text;
    private float rating;
}
