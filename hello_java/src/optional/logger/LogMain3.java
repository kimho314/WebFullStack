package optional.logger;

public class LogMain3 {
    public static void main(String[] args) {
        Logger logger = new Logger();
        logger.setDebug(true);
        logger.debug(() -> value100() + value200());

        logger.setDebug(false);
        logger.debug(() -> value100() + value200());
    }

    private static int value100() {
        System.out.println("invoke value100");
        return 100;
    }

    private static int value200() {
        System.out.println("invoke value200");
        return 200;
    }
}
