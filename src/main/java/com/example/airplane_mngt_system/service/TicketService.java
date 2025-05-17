package com.example.airplane_mngt_system.service;

import com.example.airplane_mngt_system.model.Ticket;
import com.example.airplane_mngt_system.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.airplane_mngt_system.model.Flight;
import java.time.LocalDateTime;

import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket bookTicket(String passengerName, String passengerEmail, Flight flight) {
        Ticket ticket = new Ticket(passengerName, passengerEmail, LocalDateTime.now(), flight);
        return ticketRepository.save(ticket);
    }

    // New cancelTicket method
    public boolean cancelTicket(Long ticketId) {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        if (ticket.isPresent()) {
            ticketRepository.delete(ticket.get());
            return true;
        }
        return false;
    }

    // Get ticket by ID method
    public Ticket getTicketById(Long ticketId) {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        return ticket.orElse(null);  // Returns null if ticket not found
    }
}
