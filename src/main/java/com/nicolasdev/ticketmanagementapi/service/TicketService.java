package com.nicolasdev.ticketmanagementapi.service;

import com.nicolasdev.ticketmanagementapi.dto.CreateTicketRequestDTO;
import com.nicolasdev.ticketmanagementapi.dto.TicketResponseDTO;
import com.nicolasdev.ticketmanagementapi.dto.UpdateTicketRequestDTO;
import com.nicolasdev.ticketmanagementapi.entity.Ticket;
import com.nicolasdev.ticketmanagementapi.exception.TicketNotFoundException;
import com.nicolasdev.ticketmanagementapi.mapper.TicketMapper;
import com.nicolasdev.ticketmanagementapi.repository.TicketRepository;
import com.nicolasdev.ticketmanagementapi.shared.pagination.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    public TicketResponseDTO createTicket(CreateTicketRequestDTO request){

        Ticket ticket = new Ticket();

        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setStatus("OPEN");

        Ticket savedTicket = ticketRepository.save(ticket);

        return ticketMapper.mapTicketToTicketResponseDTO(savedTicket);
    }

    public TicketResponseDTO getTicketById(Long ticketId){

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with id " + ticketId));

        return ticketMapper.mapTicketToTicketResponseDTO(ticket);
    }

    public TicketResponseDTO updateTicket(Long ticketId,
                                          UpdateTicketRequestDTO request){

        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(()->
                new TicketNotFoundException("Ticket not found with id " + ticketId));

        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setStatus(request.getStatus());

        Ticket updatedTicket = ticketRepository.save(ticket);

        return ticketMapper.mapTicketToTicketResponseDTO(updatedTicket);
    }

    public void deleteTicket(Long ticketId){

        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() ->
                new TicketNotFoundException("Ticket not found with id " + ticketId));

        ticketRepository.delete(ticket);
    }

    public PageResponse<TicketResponseDTO> getTickets(
            Pageable pageable){

        Page<Ticket> ticketPage = ticketRepository.findAll(pageable);

        return new PageResponse<>(
                ticketPage.map(ticketMapper::mapTicketToTicketResponseDTO).getContent(),
                ticketPage.getNumber(),
                ticketPage.getSize(),
                ticketPage.getTotalPages(),
                ticketPage.getTotalElements());
    }

    public List<TicketResponseDTO> getTicketsByStatus(String status){

        List<Ticket> tickets = ticketRepository.findByStatus(status);

        return tickets.stream()
                .map(ticketMapper::mapTicketToTicketResponseDTO)
                .toList();
    }
}
