package com.nicolasdev.ticketmanagementapi.dto;

import lombok.Data;

@Data
public class RegisterRequestDTO {

    private String username;

    private String password;
}
