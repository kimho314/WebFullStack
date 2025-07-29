import org.example.History;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class HistoryTest {
    @Test
    @DisplayName("create history")
    void createHistory() {
        History history = new History();
        Assertions.assertNotNull(history);
    }

    @Test
    @DisplayName("create history with parameters")
    void createHistory_withParameters() {
        Long id = 1L;
        List<String> users = List.of("kimho314@gmail.com");
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusHours(1);

        History history = new History(id, users, startDate, endDate);
        Assertions.assertNotNull(history);
        Assertions.assertEquals(id, history.getId());
        Assertions.assertIterableEquals(users, history.getUsers());
        Assertions.assertEquals(startDate, history.getStartDate());
        Assertions.assertEquals(endDate, history.getEndDate());
    }

    @Test
    @DisplayName("create history - validate start and end")
    void createHistory_validateStartAndEnd() {
        Long id = 1L;
        List<String> users = List.of("kimho314@gmail.com");
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.minusHours(1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> new History(id, users, startDate, endDate));
    }
}
