package com.ticketbookingsys.metro.util;

import com.ticketbookingsys.metro.entity.Station;
import com.ticketbookingsys.metro.exception.NotFoundException;
import com.ticketbookingsys.metro.repository.StationRepository;
import com.ticketbookingsys.metro.request.CreateTicketRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AppUtils {

    private final StationRepository stationRepository;
    public  Station findStation(String stationName) throws NotFoundException {
        Optional<Station> sourceStation = stationRepository.findOne(Example.of(Station.builder()
                .name(stationName)
                .build()));
        return sourceStation.orElseThrow(() -> new NotFoundException(stationName +" station does not exist"));
    }
}
