package com.bottle.service;

import com.bottle.DTO.PlaceDTO;
import com.bottle.entity.Place;
import com.bottle.entity.Type;
import com.bottle.repository.PlaceRepository;
import com.bottle.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {
    private PlaceRepository placeRepository;
    private TypeRepository typeRepository;

    @Autowired
    public PlaceService(PlaceRepository placeRepository, TypeRepository typeRepository) {
        this.placeRepository = placeRepository;
        this.typeRepository = typeRepository;
    }

    public void createPlace(PlaceDTO placeDTO) {
        Place place = new Place();
        place.setTitle(placeDTO.getTitle());
        place.setText(placeDTO.getText());
        place.setStartTime(placeDTO.getStartTime());
        place.setEndTime(placeDTO.getEndTime());
        place.setType(typeRepository.getOne(placeDTO.getType()));
        place.setImage(placeDTO.getImage());
        placeRepository.save(place);
    }

    public List<Type> getTypesPlace() {
        return typeRepository.findAll();
    }
}
