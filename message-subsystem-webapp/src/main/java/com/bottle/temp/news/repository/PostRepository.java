package com.bottle.temp.news.repository;


import com.bottle.temp.news.entity.Post;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface PostRepository extends CrudRepository<Post,UUID>{
    ArrayList<Post> getPostByAuthorId(UUID id);

    ArrayList<Post> getAllBy();
}

