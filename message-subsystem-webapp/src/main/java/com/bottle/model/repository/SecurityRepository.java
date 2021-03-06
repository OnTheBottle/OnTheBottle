package com.bottle.model.repository;

import com.bottle.model.entity.Security;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityRepository extends JpaRepository<Security, Integer> {

    Security getSecurityByDescription(String description);
}