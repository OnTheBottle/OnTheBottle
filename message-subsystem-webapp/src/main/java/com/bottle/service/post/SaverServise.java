package com.bottle.service.post;


import com.bottle.model.entity.Saver;
import com.bottle.model.repository.SaverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class SaverServise {

    private SaverRepository saverRepository;

    @Autowired
    public SaverServise(SaverRepository saverRepository) {
        this.saverRepository = saverRepository;
    }

    @Transactional
    public Saver getSaver(UUID id) {
        return saverRepository.findOne( id );
    }

    @Transactional
    public void create(Saver saver) {
        saverRepository.save( saver );
    }

    @Transactional
    public void update(Saver saver) {saverRepository.save( saver );
    }
}
