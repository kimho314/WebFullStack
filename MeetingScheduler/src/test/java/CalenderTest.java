import org.example.Calender;
import org.example.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class CalenderTest {
    @Test
    @DisplayName("create Calender")
    void createCalender() {
        Calender calender = new Calender();

        Assertions.assertNotNull(calender);
    }

    @Test
    @DisplayName("create Calender - add rooms")
    void createCalender_addRooms() {
        Room room1 = AbstractTest.ROOM1;

        Calender calender = new Calender();
        calender.addRooms(room1);

        Assertions.assertNotNull(calender);
        Assertions.assertEquals(1, calender.getRooms().size());
        Assertions.assertEquals(room1, calender.getRooms().getFirst());
    }

    @Test
    @DisplayName("book a room")
    void bookARoom() {
        Room room1 = AbstractTest.ROOM1;
        Room room2 = AbstractTest.ROOM2;

        Calender calender = new Calender(List.of(room1, room2));

        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusHours(2);
        calender.book(List.of(AbstractTest.USER1, AbstractTest.USER2), start, end);

        Room foundRoom1 = calender.findRoomById(room1.getId());
        Assertions.assertEquals(room1, foundRoom1);
        Assertions.assertEquals(start, foundRoom1.getStartTime());
        Assertions.assertEquals(end, foundRoom1.getEndTime());

        Room foundRoom2 = calender.findRoomById(room2.getId());
        Assertions.assertEquals(room2, foundRoom2);
        Assertions.assertNull(foundRoom2.getStartTime());
        Assertions.assertNull(foundRoom2.getEndTime());
    }
}
