package com.bottle.pubs.places.repository;

import com.bottle.pubs.places.entity.Place;

import com.bottle.pubs.places.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface PubsRepository extends JpaRepository<Place, UUID> {

    List<Place> getByName(String name);

    List<Place> getByWorkTime(String workTime);

    List<Place> getByType(String type);


}
