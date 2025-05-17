package com.example.airplane_mngt_system.repository;

import com.example.airplane_mngt_system.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
