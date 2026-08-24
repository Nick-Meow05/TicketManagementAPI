package com.nicolasdev.ticketmanagementapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateTicketRequestDTO {

    @NotBlank(
            message = "Title is required")
    @Size(
            min = 5,
            max = 100,
            message = "Title must be between 5 and 100 characters")
    private String title;

    @NotBlank(
            message = "Description is required")
    @Size(
            min = 10,
            max = 500,
            message = "Description must be between 5 and 100 characters")
    private String description;

    @NotBlank(
            message = "Status is required")
    @Size(
            min = 5,
            max = 100,
            message = "Status must be between 5 and 100 characters")
    private String status;
}
