import org.example.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RoomTest {
    @Test
    @DisplayName("create Room")
    void createRoom() {
        Room room = new Room();

        assertNotNull(room);
    }

    @Test
    @DisplayName("create Room with parameters")
    void createRoom_withParameters() {
        Long id = 1L;
        List<String> users = List.of("kimho314@gmail.com", "niluer@gmail.com");
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now().plusHours(1);

        Room room = new Room(id, users, startTime, endTime);
        assertNotNull(room);
        assertEquals(id, room.getId());
        Assertions.assertIterableEquals(users, room.getUsers());
        assertEquals(startTime, room.getStartTime());
        assertEquals(endTime, room.getEndTime());
    }

    @Test
    @DisplayName("create room - validate date")
    void createRoom_validateDate() {
        Long id = 1L;
        List<String> users = List.of("kimho314@gmail.com", "niluer@gmail.com");
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.minusHours(1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Room(id, users, startTime, endTime));
    }

    @Test
    @DisplayName("book room")
    void bookRoom() {
        Room room1 = AbstractTest.ROOM3;

        List<String> users = List.of("kimho314@gmail.com", "niluer@gmail.com");
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusDays(1);

        room1.book(users, startTime, endTime);
        Assertions.assertIterableEquals(users, room1.getUsers());
        Assertions.assertEquals(startTime, room1.getStartTime());
        Assertions.assertEquals(endTime, room1.getEndTime());
        assertEquals(1, room1.getHistories().size());
    }
}
