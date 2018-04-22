package com.bottle.pubs.places.response;

import com.bottle.pubs.places.entity.Place;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlacesListDTO {
    private List<Place> places;
}
//_
