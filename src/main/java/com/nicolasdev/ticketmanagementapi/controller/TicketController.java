package com.nicolasdev.ticketmanagementapi.controller;

import com.nicolasdev.ticketmanagementapi.dto.CreateTicketRequestDTO;
import com.nicolasdev.ticketmanagementapi.dto.TicketResponseDTO;
import com.nicolasdev.ticketmanagementapi.dto.UpdateTicketRequestDTO;
import com.nicolasdev.ticketmanagementapi.service.TicketService;
import com.nicolasdev.ticketmanagementapi.shared.pagination.dto.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Ticket Management",
        description = "Operations related to tickets")
@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @Operation(
            summary = "Create Ticket",
            description = "Creates a new support ticket" )
    @PostMapping
    public ResponseEntity<TicketResponseDTO> createTicket(@Valid @RequestBody CreateTicketRequestDTO request) {

        TicketResponseDTO response = ticketService.createTicket(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @Operation(
            summary = "Get a single ticket",
            description = "Returns one ticket by ID")
    @ApiResponses({@ApiResponse(
            responseCode = "200",
            description = "Ticket found"),
    @ApiResponse(
            responseCode = "404",
            description = "Ticket not found" )})
    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketResponseDTO> getTicketById(@PathVariable Long ticketId) {

        TicketResponseDTO response = ticketService.getTicketById(ticketId);

        return ResponseEntity.ok(response);
    }
    @Operation(
            summary = "Update ticket",
            description = "Updates an existing ticket")
    @ApiResponses({@ApiResponse(
            responseCode = "200",
            description = "Ticket found"),
    @ApiResponse(
            responseCode = "404",
            description = "Ticket not found")})
    @PutMapping("/{ticketId}")
    public ResponseEntity<TicketResponseDTO> updateTicket(@PathVariable Long ticketId, @Valid @RequestBody UpdateTicketRequestDTO request) {

        TicketResponseDTO response = ticketService.updateTicket(ticketId, request);
        return ResponseEntity.ok(response);
    }
    @Operation(
            summary = "Delete ticket",
            description = "Deletes an existing ticket")
    @ApiResponses({@ApiResponse(
            responseCode = "204",
            description = "Ticket deleted"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ticket not found")})
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long ticketId) {

        ticketService.deleteTicket(ticketId);

        return ResponseEntity.noContent().build();
    }
    @Operation(
            summary = "Retrieve all tickets with pagination and sorting",
            description = "Returns all available tickets or per page and sorted")
    @GetMapping
    public ResponseEntity<PageResponse<TicketResponseDTO>> getTickets(Pageable pageable) {

        return ResponseEntity.ok(ticketService.getTickets(pageable));
    }
    @Operation(
            summary = "Get tickets by status",
            description = "Returns tickets by status")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<TicketResponseDTO>> getTicketsByStatus(
            @PathVariable String status){

        List<TicketResponseDTO> response = ticketService.getTicketsByStatus(status);

        return ResponseEntity.ok(response);
    }
}