package com.nicolasdev.ticketmanagementapi.repository;

import com.nicolasdev.ticketmanagementapi.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository <Ticket, Long> {
}
