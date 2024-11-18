package com.jtech.usermodule.model;

import java.time.LocalDateTime;

public class User {
    private Long id;
    private String userName;
    private String password;
    private String email;
    private Role role = Role.USER;
    private LocalDateTime createdAt = LocalDateTime.now();

}
