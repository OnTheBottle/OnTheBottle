package com.bottle.userWall.home.repository;


import com.bottle.userWall.home.entity.Security;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SecurityRepository extends CrudRepository<Security,Integer> {
    Security findByName(String securityname);

}