package com.ticketbookingsys.metro.repository;

import com.ticketbookingsys.metro.entity.Ticket;
import com.ticketbookingsys.metro.service.TicketService;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, String> {
}
