package com.bottle.controller;

import com.bottle.model.entity.Like;
import com.bottle.model.entity.Post;
import com.bottle.model.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(path = "/news/like")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LikePostController {

}
