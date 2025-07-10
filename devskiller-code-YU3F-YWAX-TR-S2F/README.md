# Introduction

You are creating an [MVP](https://en.wikipedia.org/wiki/Minimum_viable_product) of an application responsible for managing flights data.

## Problem statement

1. Make tests pass by implementing missing features in the code.

2. Make the app work in a way described below.  The majority of the code is committed but there are some missing pieces to implement.

## Missing features

- Implement `Flight#reserveSeat` - which should perform a reservation for a passed grade. 

- Implement `FlightManager#findFlight(String flightNo)` - which should return an Optional with a flight for a passed `flightNo`.

- Implement `SeatsGenerator.createSeats(int rows, int seatsInRow, int firstRowNumber)` - which would generate a set of seats for the given parameters.
Each seat is represented as a string in the following format:

`<row_number><seat_in_a_row>`

where:

`<row_number>` is a decimal number of a row
`<seat_in_a_row>` is a capital letter

Example:

```
seatsGenerator.createSeats(2, 4, 1) => [Seat(1A), Seat(1B), Seat(1C), Seat(1D), Seat(2A), Seat(2B), Seat(2C), Seat(2D)]
```


