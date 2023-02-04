package com.eliezer.securepass.web.Dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class AccountDto {
    private Long id;

    private String emailAccount;

    private String usernameAccount;

    private String passwordAccount;

    private String nameAccount;
    private String descriptionAccount;

    private UserDto user;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
