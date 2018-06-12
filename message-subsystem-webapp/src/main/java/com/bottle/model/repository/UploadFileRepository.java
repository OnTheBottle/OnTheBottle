package com.bottle.model.repository;

import com.bottle.model.entity.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UploadFileRepository extends JpaRepository<UploadFile, UUID> {
    UploadFile getByName(String name);

}
