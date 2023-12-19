package com.ticketbookingsys.metro.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "stations")
@Data
@Builder
public class Station {

    @MongoId
    private String id;
    private String name;
    private Long price;
    private STATION_TYPE stationType;
}
