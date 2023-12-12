package com.ticketbookingsys.metro.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketRequest {

    @NotBlank(message = "source station cannot be empty")
    private String source;

    @NotBlank(message = "destination station cannot be empty")
    private String destination;
}
