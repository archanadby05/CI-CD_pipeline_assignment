package com.example.airplane_mngt_system.controller;

import com.example.airplane_mngt_system.model.Flight;
import com.example.airplane_mngt_system.model.Ticket;
import com.example.airplane_mngt_system.service.TicketService;
import com.example.airplane_mngt_system.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private FlightRepository flightRepository;

    // Book a ticket using a request body
    @PostMapping("/book")
    public ResponseEntity<String> bookTicket(@RequestBody TicketRequest ticketRequest) {
        // Look up the flight by its ID
        Flight flight = flightRepository.findById(ticketRequest.getFlightId()).orElse(null);

        if (flight == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Flight not found with ID: " + ticketRequest.getFlightId());
        }

        Ticket ticket = ticketService.bookTicket(ticketRequest.getPassengerName(), ticketRequest.getPassengerEmail(), flight);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ticket successfully booked with ID: " + ticket.getId());
    }

    // Cancel ticket by ID
    @DeleteMapping("/cancel/{ticketId}")
    public ResponseEntity<String> cancelTicket(@PathVariable Long ticketId) {
        boolean cancelled = ticketService.cancelTicket(ticketId);
        if (cancelled) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Ticket with ID " + ticketId + " successfully cancelled");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket with ID " + ticketId + " not found");
        }
    }

    // Get ticket by ID
    @GetMapping("/{ticketId}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long ticketId) {
        Ticket ticket = ticketService.getTicketById(ticketId);
        if (ticket == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(ticket);
    }

    // Helper class for the request body
    public static class TicketRequest {
        private String passengerName;
        private String passengerEmail;
        private Long flightId;

        // Getters and setters
        public String getPassengerName() {
            return passengerName;
        }

        public void setPassengerName(String passengerName) {
            this.passengerName = passengerName;
        }

        public String getPassengerEmail() {
            return passengerEmail;
        }

        public void setPassengerEmail(String passengerEmail) {
            this.passengerEmail = passengerEmail;
        }

        public Long getFlightId() {
            return flightId;
        }

        public void setFlightId(Long flightId) {
            this.flightId = flightId;
        }
    }
}
