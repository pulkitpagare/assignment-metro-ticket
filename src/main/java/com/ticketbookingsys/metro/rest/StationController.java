package com.ticketbookingsys.metro.rest;

import com.ticketbookingsys.metro.annotation.CustomLogExecution;
import com.ticketbookingsys.metro.annotation.LogExecutionTime;
import com.ticketbookingsys.metro.annotation.StationValidation;
import com.ticketbookingsys.metro.entity.Station;
import com.ticketbookingsys.metro.request.CreateStationRequest;
import com.ticketbookingsys.metro.service.StationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/stations")
public class StationController {

    private final StationService stationService;

    @PostMapping
    @LogExecutionTime
    @StationValidation
    @CustomLogExecution
    public ResponseEntity<Station> addStation(@RequestBody @Valid CreateStationRequest createStationRequest) {
        return ResponseEntity.ok().body(stationService.addStation(createStationRequest));
    }

    @GetMapping
    @LogExecutionTime
    @CustomLogExecution
    public ResponseEntity<List<Station>> getAllStation() {
        return ResponseEntity.ok().body(stationService.getAllStations());
    }

    @DeleteMapping("/{stationName}")
    @LogExecutionTime
    @CustomLogExecution
    public void deleteStation(@PathVariable(name = "stationName") String stationName) throws Exception {
        stationService.deleteStation(stationName);
    }

}