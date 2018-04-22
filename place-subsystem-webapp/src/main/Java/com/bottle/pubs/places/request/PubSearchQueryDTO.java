package com.bottle.pubs.places.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PubSearchQueryDTO {
    private boolean showAll;
    private String searchQuery;
    private String searchType;
}
