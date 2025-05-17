package com.example.airplane_mngt_system.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passengerName;
    private String passengerEmail;
    private LocalDateTime bookingTime;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    // Constructors, getters, setters
    public Ticket() {}

    public Ticket(String passengerName, String passengerEmail, LocalDateTime bookingTime, Flight flight) {
        this.passengerName = passengerName;
        this.passengerEmail = passengerEmail;
        this.bookingTime = bookingTime;
        this.flight = flight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
