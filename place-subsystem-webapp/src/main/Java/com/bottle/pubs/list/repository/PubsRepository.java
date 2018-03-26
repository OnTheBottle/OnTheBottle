package com.bottle.pubs.list.repository;

import com.bottle.pubs.list.entity.Place;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PubsRepository extends CrudRepository {
    List<Place> getAll();

    List<Place> getByName(String name);

    List<Place> getByTime(String time);

    List<Place> getByType(String type);

    List<Place> getAllSortByRating();

}
