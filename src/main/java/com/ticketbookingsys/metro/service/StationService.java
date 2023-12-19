package com.ticketbookingsys.metro.service;

import com.ticketbookingsys.metro.entity.Station;
import com.ticketbookingsys.metro.exception.NotFoundException;
import com.ticketbookingsys.metro.repository.StationRepository;
import com.ticketbookingsys.metro.request.CreateStationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StationService {

    private final StationRepository stationRepository;

    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    public Station addStation(CreateStationRequest createStationRequest) {
        Station station = Station.builder()
                .name(createStationRequest.getStationName())
                .price(createStationRequest.getPrice())
                .stationType(createStationRequest.getStationType())
                .build();
        return stationRepository.save(station);
    }

    public void deleteStation(String stationName) throws Exception {
        Optional<Station> station = stationRepository.findOne(Example.of(Station.builder()
                .name(stationName)
                .build()));
        station.ifPresentOrElse(stationRepository::delete, () -> {
            throw new NotFoundException("station does not exist");
        });
    }

}
