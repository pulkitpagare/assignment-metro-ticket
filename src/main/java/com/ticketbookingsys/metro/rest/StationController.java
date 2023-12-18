package com.ticketbookingsys.metro.rest;

import com.ticketbookingsys.metro.annotation.LogExecutionTime;
import com.ticketbookingsys.metro.entity.Station;
import com.ticketbookingsys.metro.request.CreateStationRequest;
import com.ticketbookingsys.metro.service.StationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/stations")
public class StationController {

    private final StationService stationService;

    @PostMapping
    @LogExecutionTime
    public ResponseEntity<Station> addStation(@RequestBody @Valid CreateStationRequest createStationRequest) {
        return ResponseEntity.ok().body(stationService.addStation(createStationRequest));
    }

    @GetMapping
    @LogExecutionTime
    public ResponseEntity<List<Station>> getAllStation() {
        return ResponseEntity.ok().body(stationService.getAllStations());
    }

    @DeleteMapping("/{stationName}")
    @LogExecutionTime
    public void deleteStation(@PathVariable(name = "stationName") String stationName) throws Exception {
        stationService.deleteStation(stationName);
    }

}