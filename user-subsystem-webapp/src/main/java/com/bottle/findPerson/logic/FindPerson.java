package com.bottle.findPerson.logic;

import com.bottle.findPerson.repository.UserRepository;
import com.bottle.findPerson.request.Request;
import com.bottle.findPerson.response.Response;

public class FindPerson {

    public Response findFromDB(UserRepository repository, Request request) {
        Response response = new Response();
        String searchQuery = request.getSearchQuery();
        String type = request.getSearchType();
        switch (type) {
            case "name":
                response.setListOfPersons(repository.getByName(searchQuery));
                break;
            case "surname":
                response.setListOfPersons(repository.getBySurname(searchQuery));
                break;
            case "city":
                response.setListOfPersons(repository.getByCity(searchQuery));
                break;
            case "country":
                response.setListOfPersons(repository.getByCountry(searchQuery));
                break;
            case "age":
                response.setListOfPersons(repository.getByAge(Integer.parseInt(searchQuery)));
                break;

        }
        return response;
    }
}
