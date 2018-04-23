package com.bottle.model.dto.responce;

import com.bottle.entity.User;
import com.bottle.model.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

// TODO: 24.04.2018 I think you understand what is wrong
public class InfoPageResponce {
    private UserDTO userDTO;
}
