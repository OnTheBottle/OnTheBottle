package com.bottle.event.service.place;

import com.bottle.event.model.entity.Place;
import com.bottle.event.model.repository.PlaceStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.UUID;

@Service
public class RegistrationPlace {
    private PlaceStore placeStore;

    @Autowired
    public RegistrationPlace(PlaceStore placeStore) {
        this.placeStore = placeStore;
    }

    public Place createOrGet(UUID id) {
        try {
            if (placeStore.exists(id)) {
                return placeStore.getById(id);
            } else {
                Place place = new Place();
                place.setId(id);
                placeStore.createOrUpdate(place);
                return place;
            }
        } catch (SQLException e) { //TODO
            e.printStackTrace();
            return null;
        }
    }

    public void save(Place place) {
        try {
            placeStore.createOrUpdate(place);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
