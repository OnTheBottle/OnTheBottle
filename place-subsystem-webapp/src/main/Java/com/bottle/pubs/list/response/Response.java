package com.bottle.pubs.list.response;

import com.bottle.pubs.list.entity.Pub;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private List<Pub> places;
}
