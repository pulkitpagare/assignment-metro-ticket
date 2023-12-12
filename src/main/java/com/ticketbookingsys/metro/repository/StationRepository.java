package com.ticketbookingsys.metro.repository;


import com.ticketbookingsys.metro.entity.Station;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends MongoRepository<Station, String> {
}
