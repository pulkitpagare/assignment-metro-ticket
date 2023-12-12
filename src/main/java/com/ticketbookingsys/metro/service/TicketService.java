package com.ticketbookingsys.metro.service;


import com.ticketbookingsys.metro.entity.Station;
import com.ticketbookingsys.metro.entity.Ticket;
import com.ticketbookingsys.metro.exception.NotFoundException;
import com.ticketbookingsys.metro.repository.StationRepository;
import com.ticketbookingsys.metro.repository.TicketRepository;
import com.ticketbookingsys.metro.request.CreateTicketRequest;
import com.ticketbookingsys.metro.util.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final StationRepository stationRepository;
    private final TicketRepository ticketRepository;
    private final AppUtils appUtils;
    public Ticket generateTicket(CreateTicketRequest createTicketRequest) throws NotFoundException {
        Station sourceStation = appUtils.findStation(createTicketRequest.getSource());
        Station destinationStation = appUtils.findStation(createTicketRequest.getDestination());
        Ticket ticket = Ticket.builder()
                .source(createTicketRequest.getSource())
                .destination(createTicketRequest.getDestination())
                .price(Math.abs(sourceStation.getPrice() - destinationStation.getPrice()))
                .localDateTime(LocalDateTime.now())
                .build();
        return ticketRepository.save(ticket);
    }


}
