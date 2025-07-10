package com.airline.manager.model.seat;

import java.util.HashSet;
import java.util.Set;

public class SeatsGenerator {

	public Set<Seat> createSeats(int rows, int seatsInRow, int firstRowNumber) {
		HashSet<Seat> set = new HashSet<>();
		for(int i=firstRowNumber;i<rows+firstRowNumber;i++) {
			for(int j=0;j<seatsInRow;j++) {
				char ch = (char)('A' + j);
				String seatNumber = i+String.valueOf(ch);
				set.add(new Seat(seatNumber));
			}
		}
		return set; // TODO: Implement
	}
}
