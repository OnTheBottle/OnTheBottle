package com.bottle.repository;

import com.bottle.entity.Place;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PubsRepository extends JpaRepository<Place, UUID> {
}
