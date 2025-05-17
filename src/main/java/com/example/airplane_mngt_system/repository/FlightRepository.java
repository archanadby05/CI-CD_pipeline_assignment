package com.example.airplane_mngt_system.repository;

import com.example.airplane_mngt_system.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    // Custom query to find flights by ID and date range
    List<Flight> findByIdAndDepartureTimeBetween(Long id, LocalDateTime startDate, LocalDateTime endDate);
}
