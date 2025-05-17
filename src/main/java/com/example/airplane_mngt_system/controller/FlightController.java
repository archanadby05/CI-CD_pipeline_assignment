package com.example.airplane_mngt_system.controller;

import com.example.airplane_mngt_system.model.Flight;
import com.example.airplane_mngt_system.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    // Schedule a new flight
    @PostMapping
    public ResponseEntity<Flight> scheduleFlight(@RequestBody Flight flight) {
        Flight scheduledFlight = flightService.scheduleFlight(flight);
        return ResponseEntity.status(201).body(scheduledFlight);
    }

    // Get all scheduled flights (with sorting)
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights(@RequestParam(defaultValue = "asc") String sort) {
        Sort.Direction direction = sort.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        List<Flight> flights = flightService.getAllFlights(direction);
        return ResponseEntity.ok(flights);
    }

    // Get flight schedules by date range
    @GetMapping("/{id}/schedules")
    public ResponseEntity<List<Flight>> getFlightSchedulesByDateRange(
            @PathVariable Long id,
            @RequestParam("dates") String dates) {

        // Parse the dates parameter (in format "startDate,endDate")
        String[] dateArray = dates.split(",");
        LocalDate startDate = LocalDate.parse(dateArray[0]);
        LocalDate endDate = LocalDate.parse(dateArray[1]);

        List<Flight> flights = flightService.getFlightSchedulesByDateRange(id, startDate, endDate);
        return ResponseEntity.ok(flights);
    }
}
