package com.bottle.model.DTO.response;

import lombok.Data;

import java.util.List;

@Data
public class ListResponseDTO<T> {
    private List<T> list;
}
