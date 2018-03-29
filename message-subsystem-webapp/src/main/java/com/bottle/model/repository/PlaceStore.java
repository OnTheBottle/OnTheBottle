package com.bottle.model.repository;

import com.bottle.model.entity.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Service
public class PlaceStore {
    private PlaceRepository placeRepository;

    @Autowired
    public PlaceStore(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Transactional
    public void createOrUpdate(Place place) throws SQLException {
        placeRepository.save(place);
    }

    @Transactional
    public void delete(UUID id) throws SQLException {
        placeRepository.delete(id);
    }

    @Transactional
    public Place getById(UUID id) throws SQLException {
        return placeRepository.getOne(id);
    }

    @Transactional
    public List<Place> getAll() throws SQLException {
        return placeRepository.findAll();
    }

    @Transactional
    public boolean exists(UUID id) throws SQLException {
        return placeRepository.exists(id);
    }
}
