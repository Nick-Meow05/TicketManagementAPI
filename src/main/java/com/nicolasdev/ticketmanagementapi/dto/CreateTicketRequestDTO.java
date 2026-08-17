package com.nicolasdev.ticketmanagementapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateTicketRequestDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String description;
}
