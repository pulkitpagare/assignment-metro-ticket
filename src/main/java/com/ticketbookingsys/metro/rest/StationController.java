package com.ticketbookingsys.metro.rest;

import com.ticketbookingsys.metro.annotation.LogExecutionTime;
import com.ticketbookingsys.metro.entity.Station;
import com.ticketbookingsys.metro.exception.NotFoundException;
import com.ticketbookingsys.metro.repository.StationRepository;
import com.ticketbookingsys.metro.request.CreateStationRequest;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stations")
@Slf4j
public class StationController {

    private final StationRepository stationRepository;

    @GetMapping
    public ResponseEntity<List<Station>> getAllStations(){
        return ResponseEntity.ok().body(stationRepository.findAll());
    }
    @PostMapping
    @LogExecutionTime
    public ResponseEntity<Station> addStation(@RequestBody @Valid CreateStationRequest createStationRequest){
        Station station = Station.builder()
                .name(createStationRequest.getStationName())
                .price(createStationRequest.getPrice())
                .build();
        return ResponseEntity.ok().body(stationRepository.save(station));
    }
    @DeleteMapping("/{stationName}")
    public void deleteStation(@PathVariable(name = "stationName") String stationName) throws Exception{
        Optional<Station> station = stationRepository.findOne(Example.of(Station.builder()
                .name(stationName)
                .build()));
        station.ifPresentOrElse(stationRepository::delete, () -> {
            throw new NotFoundException("station does not exist");
        });
    }
}
