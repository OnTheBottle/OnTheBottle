package com.bottle.event.service.place;

import com.bottle.event.model.entity.Place;
import com.bottle.event.model.repository.PlaceStore;
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
}
