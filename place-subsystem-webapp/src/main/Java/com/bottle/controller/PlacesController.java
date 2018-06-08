package com.bottle.controller;


import com.bottle.DTO.PlaceDTO;
import com.bottle.entity.Place;
import com.bottle.entity.Type;
import com.bottle.service.AuthService;
import com.bottle.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
public class PlacesController {
    private AuthService authService;
    private PlaceService placeService;

    @Autowired
    public PlacesController(AuthService authService, PlaceService placeService) {
        this.authService = authService;
        this.placeService = placeService;
    }

    @RequestMapping(path = "/createPlace", method = RequestMethod.POST)
    public ResponseEntity<?> createEvent(@RequestBody PlaceDTO place,
                                         @RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return ErrorResponse.getErrorResponse("Non-valid token");

        placeService.createPlace(place);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/getTypesPlace", method = RequestMethod.GET)
    public ResponseEntity<?> getTypesPlace(@RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return ErrorResponse.getErrorResponse("Non-valid token");

        List<Type> typesPlace = placeService.getTypesPlace();
        return new ResponseEntity<>(typesPlace, HttpStatus.OK);
    }

    @RequestMapping(path = "/getPlaces", method = RequestMethod.GET)
    public ResponseEntity<?> getPlaces(@RequestParam(name = "access_token") String token) {
        if (!authService.isValidToken(token)) return ErrorResponse.getErrorResponse("Non-valid token");

        List<Place> places = placeService.getPlaces();
        return new ResponseEntity<>(places, HttpStatus.OK);
    }
}
