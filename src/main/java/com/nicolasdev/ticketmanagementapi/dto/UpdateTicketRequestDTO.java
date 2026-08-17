package com.nicolasdev.ticketmanagementapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateTicketRequestDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String status;
}
