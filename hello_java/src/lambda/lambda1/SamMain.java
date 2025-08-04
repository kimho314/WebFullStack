package lambda.lambda1;

public class SamMain {
    public static void main(String[] args) {
        SamInterface samInterface = () -> System.out.println("Hello, Sam!");
        samInterface.run();
        
        NotSamInterface notSamInterface = new NotSamInterface() {
            @Override
            public void run() {
                System.out.println("Hello, NotSam!");
            }

            @Override
            public void go() {
                System.out.println("Go, NotSam!");
            }
        };
        notSamInterface.run();
        notSamInterface.go();
    }
}
