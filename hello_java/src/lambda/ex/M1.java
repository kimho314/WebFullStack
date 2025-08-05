package lambda.ex;

public class M1 {
    interface M1Interface {
        void run();
    }

    private static class M1Class {
        public void run(M1Interface m1Interface) {
            System.out.println("=== start ===");
            m1Interface.run();
            System.out.println("=== end ===");
        }
    }

    public static void main(String[] args) {
        M1Class m1Class = new M1Class();
        m1Class.run(() -> System.out.println("Good Morning"));
        m1Class.run(() -> System.out.println("Good Afternoon"));
        m1Class.run(() -> System.out.println("Good Evening"));
    }
}
