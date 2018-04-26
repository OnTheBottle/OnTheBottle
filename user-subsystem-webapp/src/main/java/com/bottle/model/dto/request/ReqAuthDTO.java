package com.bottle.model.dto.request;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReqAuthDTO {
    private String login;
    private String password;
}
