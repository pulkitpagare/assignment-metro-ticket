package com.ticketbookingsys.metro.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateStationRequest {
    /*
    * TODO : price restrictions on Stations
    *  accept a new parameter stationType
    *  if stationType are of this category (LUX, SEMI-LUX) then price should be minimum 20
    *   if stationType are of this category (NON-LUX) then price should be maximum 50
    * */
    @NotBlank(message = "station name must not null")
    private String stationName;

    @Positive(message = "price should be positive number")
    private Long price;

}
