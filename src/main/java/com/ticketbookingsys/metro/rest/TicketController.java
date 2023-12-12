package com.ticketbookingsys.metro.rest;

import com.ticketbookingsys.metro.entity.Ticket;
import com.ticketbookingsys.metro.exception.NotFoundException;
import com.ticketbookingsys.metro.request.CreateTicketRequest;
import com.ticketbookingsys.metro.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    @PostMapping("/buyTicket")
    public Ticket buyTickets(@RequestBody @Valid CreateTicketRequest createTicketRequest) throws NotFoundException {
        return ticketService.generateTicket(createTicketRequest);
    }
}
