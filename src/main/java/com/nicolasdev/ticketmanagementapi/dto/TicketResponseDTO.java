package com.nicolasdev.ticketmanagementapi.dto;

public record TicketResponseDTO(
        long ticketId,
        String title,
        String status) {}

