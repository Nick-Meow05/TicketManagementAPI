package com.nicolasdev.ticketmanagementapi.mapper;

import com.nicolasdev.ticketmanagementapi.dto.TicketResponseDTO;
import com.nicolasdev.ticketmanagementapi.entity.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    // Map the Ticket Entity to TicketResponseDTO Object
    //Map OWNER to RECEIVER - set the value from owner(Ticket) to Receiver (DTO)
    public TicketResponseDTO mapTicketToTicketResponseDTO(Ticket ticket){
       return new TicketResponseDTO(
                ticket.getTicketId(),
                ticket.getTitle(),
                ticket.getStatus());
    }
}
