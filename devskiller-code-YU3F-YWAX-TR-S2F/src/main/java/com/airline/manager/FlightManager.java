package com.airline.manager;

import com.airline.manager.model.flight.Flight;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class FlightManager {

	private Set<Flight> flights = new HashSet<>();

	public boolean addNewFlight(Flight flight) {
		return flights.add(flight);
	}

	public Optional<Flight> findFlight(String flightNo) {
		return flights.stream().filter(it -> it.getFlightNo().equals(flightNo)).findFirst(); // TODO: Implement
	}

}
