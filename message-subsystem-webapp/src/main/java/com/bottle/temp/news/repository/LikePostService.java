package com.bottle.temp.news.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikePostService {
    @Autowired
    private LikePostRepository likePostRepository;
}
