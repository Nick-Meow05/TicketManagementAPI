package com.nicolasdev.ticketmanagementapi.controller;

import com.nicolasdev.ticketmanagementapi.dto.CreateTicketRequestDTO;
import com.nicolasdev.ticketmanagementapi.dto.TicketResponseDTO;
import com.nicolasdev.ticketmanagementapi.dto.UpdateTicketRequestDTO;
import com.nicolasdev.ticketmanagementapi.service.TicketService;
import com.nicolasdev.ticketmanagementapi.shared.pagination.dto.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketResponseDTO> createTicket(@Valid @RequestBody CreateTicketRequestDTO request) {

        TicketResponseDTO response = ticketService.createTicket(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TicketResponseDTO>> getAllTickets() {

        List<TicketResponseDTO> response = ticketService.getAllTickets();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketResponseDTO> getTicketById(@PathVariable Long ticketId) {

        TicketResponseDTO response = ticketService.getTicketById(ticketId);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{ticketId}")
    public ResponseEntity<TicketResponseDTO> updateTicket(@PathVariable Long ticketId, @Valid @RequestBody UpdateTicketRequestDTO request) {

        TicketResponseDTO response = ticketService.updateTicket(ticketId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long ticketId) {

        ticketService.deleteTicket(ticketId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paged")
    public ResponseEntity<PageResponse<TicketResponseDTO>> getTicketsPaged(Pageable pageable) {

        return ResponseEntity.ok(ticketService.getTickets(pageable));
    }
}