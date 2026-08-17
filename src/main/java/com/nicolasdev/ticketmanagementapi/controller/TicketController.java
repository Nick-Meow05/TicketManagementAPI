package com.nicolasdev.ticketmanagementapi.controller;

import com.nicolasdev.ticketmanagementapi.dto.CreateTicketRequestDTO;
import com.nicolasdev.ticketmanagementapi.dto.TicketResponseDTO;
import com.nicolasdev.ticketmanagementapi.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public TicketResponseDTO createTicket(
            @Valid
            @RequestBody CreateTicketRequestDTO request){

        return ticketService.createTicket(request);
    }

    @GetMapping
    public List<TicketResponseDTO> getAllTickets(){

        return ticketService.getAllTickets();
    }

    @GetMapping("/{ticketId}")
    public TicketResponseDTO getTicketById(
            @PathVariable Long ticketId){

        return ticketService.getTicketById(ticketId);
    }
}
