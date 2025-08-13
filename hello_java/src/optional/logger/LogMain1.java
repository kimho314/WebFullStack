package optional.logger;

public class LogMain1 {
    public static void main(String[] args) {
        Logger logger = new Logger();
        logger.setDebug(true);
        logger.debug(10 + 20);

        logger.setDebug(false);
        logger.debug(10 + 20);
    }
}
