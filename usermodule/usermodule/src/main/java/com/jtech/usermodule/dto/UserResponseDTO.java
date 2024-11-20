package com.jtech.usermodule.dto;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String userName;
    private String email;
    private String password;
}
