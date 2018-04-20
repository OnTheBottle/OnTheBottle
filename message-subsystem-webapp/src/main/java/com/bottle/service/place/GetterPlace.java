package com.bottle.service.place;

import com.bottle.model.entity.Place;
import com.bottle.model.repository.PlaceStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GetterPlace {
    private PlaceStore placeStore;

    @Autowired
    public GetterPlace(PlaceStore placeStore) {
        this.placeStore = placeStore;
    }

    public List<UUID> getAllId() {
        try {
            List<UUID> uuids = new ArrayList<>();
            List<Place> places = placeStore.getAll();

            for (Place place : places) {
                uuids.add(place.getId());
            }

            return uuids;
        } catch (SQLException e) { // TODO
            e.printStackTrace();
            return null;
        }
    }

    public Place getPlace(UUID id) {
        try {
            if (placeStore.exists(id)) {
                return placeStore.getById(id);
            } else {
                return createPlace(id, "", "");
            }
        } catch (SQLException e) { //TODO
            e.printStackTrace();
            return null;
        }
    }

    public Place createPlace(UUID id, String title, String avatar) {
        try {
            Place place = new Place();
            place.setId(id);
            place.setTitle(title);
            place.setAvatar(avatar);
            placeStore.createOrUpdate(place);
            return place;
        } catch (SQLException e) { //TODO
            e.printStackTrace();
            return null;
        }
    }
}
