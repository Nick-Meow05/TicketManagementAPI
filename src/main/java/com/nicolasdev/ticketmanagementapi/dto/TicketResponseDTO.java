package com.nicolasdev.ticketmanagementapi.dto;

import lombok.Data;

@Data
public class TicketResponseDTO {

    private Long ticketId;

    private String title;

    private String status;
}
