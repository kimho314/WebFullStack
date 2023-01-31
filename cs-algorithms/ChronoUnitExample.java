import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

public class ChronoUnitExample {
    public static void main(String[] args) {
        var offsetDateTime1 = OffsetDateTime.of(LocalDateTime.of(2023, 1, 30, 12, 0, 0), ZoneOffset.UTC);
        var offsetDateTime2 = OffsetDateTime.of(LocalDateTime.of(2023, 1, 30, 12, 30, 0), ZoneOffset.UTC);
        var betweenInMinute = ChronoUnit.MINUTES.between(offsetDateTime1, offsetDateTime2);
        System.out.println(betweenInMinute);
        betweenInMinute = ChronoUnit.SECONDS.between(offsetDateTime1, offsetDateTime2);
        System.out.println(betweenInMinute > 30 * 60);
    }
}
