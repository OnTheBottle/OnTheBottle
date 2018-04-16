package com.bottle.model.dto.responce;

import com.bottle.model.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FindPersonResponse {
    private List<UserDTO> listOfPersons;
}