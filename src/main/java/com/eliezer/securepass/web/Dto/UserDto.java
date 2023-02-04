package com.eliezer.securepass.web.Dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String email;
    private String password;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
