package com.nicolasdev.ticketmanagementapi.repository;

import com.nicolasdev.ticketmanagementapi.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository <Ticket, Long> {

    List<Ticket> findByStatus(String status);
}
