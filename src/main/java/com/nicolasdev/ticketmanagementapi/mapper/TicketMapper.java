package com.nicolasdev.ticketmanagementapi.mapper;

import com.nicolasdev.ticketmanagementapi.dto.TicketResponseDTO;
import com.nicolasdev.ticketmanagementapi.entity.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    // Map the Ticket Entity to TicketResponseDTO Object
    //Map OWNER to RECEIVER - set the value from owner(Ticket) to Receiver (DTO)
    public TicketResponseDTO mapTicketToTicketResponseDTO(Ticket ticket){
        TicketResponseDTO response = new TicketResponseDTO();
        response.setTicketId(ticket.getTicketId());
        response.setTitle(ticket.getTitle());
        response.setStatus(ticket.getStatus());

        return response;
    }
}
