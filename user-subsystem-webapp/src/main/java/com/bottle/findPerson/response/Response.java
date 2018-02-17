package com.bottle.findPerson.response;

import com.bottle.findPerson.entity.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private List<UserDTO> listOfPersons;
}