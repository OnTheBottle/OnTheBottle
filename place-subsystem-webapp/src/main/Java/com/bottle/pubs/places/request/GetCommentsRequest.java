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
public class GetCommentsRequest {
    private UUID place_id;
    private int start_number;
    private int end_number;

}
