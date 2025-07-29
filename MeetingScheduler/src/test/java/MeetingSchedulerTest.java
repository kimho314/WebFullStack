import org.example.MeetingScheduler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MeetingSchedulerTest {
    @Test
    @DisplayName("create MeetingSchduler")
    void createMeetingScheduler() {
        MeetingScheduler meetingScheduler = new MeetingScheduler();

        assertNotNull(meetingScheduler);
    }
}
