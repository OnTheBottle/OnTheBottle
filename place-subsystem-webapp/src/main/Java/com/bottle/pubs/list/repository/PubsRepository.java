package com.bottle.pubs.list.repository;

import com.bottle.pubs.list.entity.Place;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface PubsRepository extends CrudRepository<Place, UUID> {

    List<Place> getByName(String name);

    List<Place> getByWorkTime(String workTime);

    List<Place> getByType(String type);


}
