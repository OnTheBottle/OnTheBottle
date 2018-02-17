package com.bottle.pubs.list.repository;

import com.bottle.pubs.list.entity.Pub;

import java.util.List;

public interface PubsRepository extends CrudRepository {
    List<Pub> getAll();
    List<Pub> getByName (String name);
    List<Pub> getByTime (String time);
    List<Pub> getByType (String tipe);

}
