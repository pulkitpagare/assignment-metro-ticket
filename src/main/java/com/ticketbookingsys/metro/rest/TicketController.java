package com.ticketbookingsys.metro.rest;

import com.ticketbookingsys.metro.annotation.LogExecutionTime;
import com.ticketbookingsys.metro.entity.Ticket;
import com.ticketbookingsys.metro.exception.NotFoundException;
import com.ticketbookingsys.metro.request.CreateTicketRequest;
import com.ticketbookingsys.metro.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    @LogExecutionTime
    @PostMapping("/buyTicket")
    public ResponseEntity<Ticket> buyTickets(@RequestBody @Valid CreateTicketRequest createTicketRequest) throws NotFoundException {
        return ResponseEntity.ok().body(ticketService.generateTicket(createTicketRequest));
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<Ticket> getTicketDetails(@PathVariable(name = "ticketId") String ticketId) throws NotFoundException {
        return ResponseEntity.ok().body(ticketService.getTicketDetails(ticketId));
    }

    @LogExecutionTime
    @PatchMapping("/updateEntry/{ticketId}")
    public void updateEntry(@PathVariable(name = "ticketId") String ticketId) throws Exception {
        log.info("inside {} controller", StationController.class);
        ticketService.updateEntry(ticketId);
    }

    @LogExecutionTime
    @PatchMapping("/updateExit/{ticketId}")
    public void updateExit(@PathVariable(name = "ticketId") String ticketId) throws Exception {
        log.info("inside {} controller", StationController.class);
        ticketService.updateExit(ticketId);
    }

}
