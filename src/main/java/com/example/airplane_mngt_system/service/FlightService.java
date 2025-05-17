package com.example.airplane_mngt_system.service;

import com.example.airplane_mngt_system.model.Flight;
import com.example.airplane_mngt_system.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    // Updated method to accept direction parameter for sorting
    public List<Flight> getAllFlights(Sort.Direction direction) {
        return flightRepository.findAll(Sort.by(direction, "departureTime"));  // Sort by departureTime
    }

    // Method to schedule a new flight
    public Flight scheduleFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    // Implement the method to get flights by date range
    public List<Flight> getFlightSchedulesByDateRange(Long id, LocalDate startDate, LocalDate endDate) {
        // Convert LocalDate to LocalDateTime (start of day and end of day)
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);

        // Fetch the flights by date range and id
        return flightRepository.findByIdAndDepartureTimeBetween(id, startDateTime, endDateTime);
    }
}
