package com.nicolasdev.ticketmanagementapi.service;

import com.nicolasdev.ticketmanagementapi.dto.CreateTicketRequestDTO;
import com.nicolasdev.ticketmanagementapi.dto.TicketResponseDTO;
import com.nicolasdev.ticketmanagementapi.entity.Ticket;
import com.nicolasdev.ticketmanagementapi.exception.TicketNotFoundException;
import com.nicolasdev.ticketmanagementapi.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<TicketResponseDTO> getAllTickets(){

        List<Ticket> tickets = ticketRepository.findAll();

        List<TicketResponseDTO> responses = new ArrayList<>();

        for (Ticket ticket : tickets){

            TicketResponseDTO responseDTO = new TicketResponseDTO();

            responseDTO.setTicketId(ticket.getTicketId());
            responseDTO.setTitle(ticket.getTitle());
            responseDTO.setStatus(ticket.getStatus());

            responses.add(responseDTO);
        }
        return responses;
    }

    public TicketResponseDTO getTicketById(Long ticketId){

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with id " + ticketId));

        TicketResponseDTO response = new TicketResponseDTO();

        response.setTicketId(ticket.getTicketId());
        response.setTitle(ticket.getTitle());
        response.setStatus(ticket.getStatus());

        return response;
    }
}
