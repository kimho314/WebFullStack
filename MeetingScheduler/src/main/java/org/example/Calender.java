package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Calender {
    private List<Room> rooms;

    public Calender() {
        this(new ArrayList<>());
    }

    public Calender(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void addRooms(Room... rooms) {
        this.rooms.addAll(List.of(rooms));
    }


    public void book(List<String> users, LocalDateTime start, LocalDateTime end) {
        Room room = this.rooms.stream()
                .filter(it -> it.isAvailable(start, end))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("no available room"));
        room.book(users, start, end);
    }

    public Room findRoomById(Long id) {
        return this.rooms.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("room not found"));
    }
}
