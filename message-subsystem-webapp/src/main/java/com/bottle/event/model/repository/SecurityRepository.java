package com.bottle.event.model.repository;


import com.bottle.event.model.entity.Security;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SecurityRepository extends CrudRepository<Security, Integer> {
    Security findByName(String securityname);

}