package org.example;

import java.time.LocalDateTime;

public class DateChecker {
    public static void validateDate(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null && endDate == null) {
            return;
        }
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("start and end date must not be null");
        }
        if (endDate.isBefore(startDate) || endDate.isEqual(startDate)) {
            throw new IllegalArgumentException("end date must be after start date");
        }
    }
}
