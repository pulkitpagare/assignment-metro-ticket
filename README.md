# Online Metro Ticket Booking System

## Overview
This project is a full stack online metro ticket booking system allowing users to book tickets between two stations. A unique ID is generated for each ticket, serving as the ticket itself. This system is designed for simplicity and efficiency, without the need for user login or authorization.

## Features
- **Ticket Booking**: Users can book tickets between any two stations.
- **ID Generation**: Each ticket comes with a unique ID, valid for 18 hours.
- **Usage Limit**: The ID can be used twice: once for entering and once for exiting a station.
- **Entry and Exit Validation**: The ID must be used for entry before it can be used for exit.

## Technology Stack
- **Frontend**: Choice of frontend framework.
- **Backend**: SpringBoot (Java).
- **Database**: Flexible choice for storing ticket information.

## Data Structure
- **JSON for Station Data**: The system uses a JSON file to store information about stations and ticket prices.
- **Example JSON Structure**:
  ```json
  {
    "stations": {
      "A": { "startStation": true, "price": 0 },
      "B": { "price": 5 },
      "C": { "price": 15 },
      "D": { "lastStation": true, "price": 50 }
    }
  }