package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private Long id;
    private List<String> users;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<History> histories;

    public Room() {
        this(null, new ArrayList<>(), null, null);
    }

    public Room(Long id) {
        this(id, new ArrayList<>(), null, null);
    }

    public Room(Long id, List<String> users, LocalDateTime startTime, LocalDateTime endTime) {
        DateChecker.validateDate(startTime, endTime);
        this.id = id;
        this.users = users;
        this.startTime = startTime;
        this.endTime = endTime;
        this.histories = new ArrayList<>();
    }

    private void createHistory() {
        long id = IdentifierUtil.generate();
        History history = new History(id, this.users, this.startTime, this.endTime);
        this.histories.add(history);
    }

    public Long getId() {
        return id;
    }

    public List<String> getUsers() {
        return users;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public List<History> getHistories() {
        return histories;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Room room = (Room) o;
        return id.equals(room.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public void book(List<String> users, LocalDateTime startTime, LocalDateTime endTime) {
        DateChecker.validateDate(startTime, endTime);
        this.users.addAll(users);
        this.startTime = startTime;
        this.endTime = endTime;
        createHistory();
    }

    public boolean isAvailable(LocalDateTime start, LocalDateTime end) {
        if (this.startTime == null && this.endTime == null) {
            return true;
        }
        if (this.endTime.isBefore(start)) {
            return true;
        }
        return false;
    }
}
