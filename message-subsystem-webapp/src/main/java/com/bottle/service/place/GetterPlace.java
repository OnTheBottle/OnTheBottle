package com.bottle.service.place;

import com.bottle.model.entity.Place;
import com.bottle.model.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GetterPlace {
    private PlaceRepository placeRepository;

    @Autowired
    public GetterPlace(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<UUID> getAllId() {
        List<UUID> uuids = new ArrayList<>();
        List<Place> places = placeRepository.findAll();

        for (Place place : places) {
            uuids.add(place.getId());
        }

        return uuids;
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
