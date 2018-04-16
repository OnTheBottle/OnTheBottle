package com.bottle.model.repository;


import com.bottle.model.entity.Security;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityRepository extends JpaRepository<Security, Integer> {
    Security findByName(String securityname);
}