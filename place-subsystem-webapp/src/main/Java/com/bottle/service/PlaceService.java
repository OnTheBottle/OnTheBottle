package com.bottle.service;

import com.bottle.DTO.PlaceDTO;
import com.bottle.entity.Place;
import com.bottle.entity.Type;
import com.bottle.repository.PlaceRepository;
import com.bottle.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlaceService {
    private PlaceRepository placeRepository;
    private TypeRepository typeRepository;

    @Autowired
    public PlaceService(PlaceRepository placeRepository, TypeRepository typeRepository) {
        this.placeRepository = placeRepository;
        this.typeRepository = typeRepository;
    }

    public String createPlace(PlaceDTO placeDTO) {
        UUID id = UUID.randomUUID();

        Place place = new Place();
        place.setId(id);
        place.setTitle(placeDTO.getTitle());
        place.setText(placeDTO.getText());
        place.setStartTime(placeDTO.getStartTime());
        place.setEndTime(placeDTO.getEndTime());
        place.setType(typeRepository.getOne(placeDTO.getType()));
        place.setImage(placeDTO.getImage());
        placeRepository.save(place);

        return id.toString();
    }

    public List<Type> getTypesPlace() {
        return typeRepository.findAll();
    }

    public List<Place> getPlaces() {
        return placeRepository.findAll();
    }
}
