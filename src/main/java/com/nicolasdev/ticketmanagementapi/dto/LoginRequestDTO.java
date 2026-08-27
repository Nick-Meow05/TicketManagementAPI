package com.nicolasdev.ticketmanagementapi.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {

    private String username;

    private String password;
}
