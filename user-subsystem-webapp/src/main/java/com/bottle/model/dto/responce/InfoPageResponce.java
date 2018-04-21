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

public class InfoPageResponce {
    private UserDTO userDTO;
}
