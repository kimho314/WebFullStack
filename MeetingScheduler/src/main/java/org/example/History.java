package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class History {
    private Long id;
    private List<String> users;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public History() {
        this(null, new ArrayList<>(), null, null);
    }

    public History(Long id, List<String> users, LocalDateTime startDate, LocalDateTime endDate) {
        DateChecker.validateDate(startDate, endDate);
        this.id = id;
        this.users = users;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public List<String> getUsers() {
        return users;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        History history = (History) o;
        return id.equals(history.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
