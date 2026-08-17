package com.nicolasdev.ticketmanagementapi.service;

import com.nicolasdev.ticketmanagementapi.dto.CreateTicketRequestDTO;
import com.nicolasdev.ticketmanagementapi.dto.TicketResponseDTO;
import com.nicolasdev.ticketmanagementapi.entity.Ticket;
import com.nicolasdev.ticketmanagementapi.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketResponseDTO createTicket(CreateTicketRequestDTO request){

        Ticket ticket = new Ticket();

        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setStatus("OPEN");

        Ticket savedTicket = ticketRepository.save(ticket);

        TicketResponseDTO response = new TicketResponseDTO();

        response.setTicketId(savedTicket.getTicketId());
        response.setTitle(savedTicket.getTitle());
        response.setStatus(savedTicket.getStatus());

        return response;
    }
}
