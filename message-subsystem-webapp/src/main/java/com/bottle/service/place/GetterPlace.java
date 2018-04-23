package com.bottle.service.place;

import com.bottle.model.entity.Place;
import com.bottle.model.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GetterPlace {
    private PlaceRepository placeRepository;

    @Autowired
    public GetterPlace(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<Place> getAll() {
        return placeRepository.findAll();
    }

    public Place getPlace(UUID id) {
        if (placeRepository.exists(id)) {
            return placeRepository.getOne(id);
        } else {
            return createPlace(id, "", "");
        }
    }

    public Place createPlace(UUID id, String title, String avatar) {
        Place place = new Place();
        place.setId(id);
        place.setTitle(title);
        place.setAvatar(avatar);
        placeRepository.save(place);
        return place;
    }
}
