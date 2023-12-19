package com.ticketbookingsys.metro.service;


import com.ticketbookingsys.metro.annotation.LogExecutionTime;
import com.ticketbookingsys.metro.entity.Station;
import com.ticketbookingsys.metro.entity.Ticket;
import com.ticketbookingsys.metro.exception.AlreadyUsedTicketException;
import com.ticketbookingsys.metro.exception.NotFoundException;
import com.ticketbookingsys.metro.exception.NotUsedTicketException;
import com.ticketbookingsys.metro.repository.TicketRepository;
import com.ticketbookingsys.metro.request.CreateTicketRequest;
import com.ticketbookingsys.metro.util.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final AppUtils appUtils;

    public Ticket getTicketDetails(String ticketId) throws NotFoundException {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        return ticket.orElseThrow(() -> new  NotFoundException("Ticket Id " + ticketId + " doesn't exist!!!"));
    }

//    @LogExecutionTime
    public Ticket generateTicket(CreateTicketRequest createTicketRequest) throws NotFoundException {
        Station sourceStation = appUtils.findStation(createTicketRequest.getSource());
        Station destinationStation = appUtils.findStation(createTicketRequest.getDestination());
        Ticket ticket = Ticket.builder()
                .source(createTicketRequest.getSource())
                .destination(createTicketRequest.getDestination())
                .price(Math.abs(sourceStation.getPrice() - destinationStation.getPrice()))
                .localDateTime(LocalDateTime.now())
                .isEntry(false)
                .isExit(false)
                .build();
        return ticketRepository.save(ticket);
    }

    @LogExecutionTime
    public void updateEntry(String ticketId) throws Exception {
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
        ticketOptional.ifPresentOrElse(
                (ticket) -> {
                    if(ticket.isEntry()) {
                        throw new AlreadyUsedTicketException("ticket id " + ticketId + " is already used!");
                    } else {
                        ticket.setEntry(true);
                        ticketRepository.save(ticket);
                    }
                },
                () -> {
                    throw new NotFoundException("ticket id " + ticketId + " doesn't exists!");
                }
        );
    }

    @LogExecutionTime
    public void updateExit(String ticketId) throws Exception {
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
        ticketOptional.ifPresentOrElse(
                (ticket) -> {
                    if(!ticket.isEntry()) {
                        throw new NotUsedTicketException("ticket id " + ticketId + " is not entered yet!");
                    }
                    if(ticket.isExit()) {
                        throw new AlreadyUsedTicketException("ticket id " + ticketId + " is already used!");
                    } else {
                        ticket.setExit(true);
                        ticketRepository.save(ticket);
                    }
                },
                () -> {
                    throw new NotFoundException("ticket id " + ticketId + " doesn't exists!");
                }
        );
    }

}
