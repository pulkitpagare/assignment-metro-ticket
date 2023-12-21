package com.ticketbookingsys.metro.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Document(collection = "tickets")
@Data
@Builder
public class Ticket {

    @MongoId
    private String id;
    private String source;
    private String destination;
    private Long price;
    private LocalDateTime localDateTime;
    private boolean isEntry;
    private boolean isExit;
}
