package com.bottle.model.repository;

import com.bottle.model.entity.Saver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SaverRepository extends JpaRepository<Saver, UUID> {
}

