package lambda.lambda6;

public class OuterMain {
    private String message = "outer class";

    public void execute() {
        Runnable runnable = new Runnable() {
            private String message = "inner class";

            @Override
            public void run() {
                System.out.println("[annonymous inner class] message = " + message);
                System.out.println("[annonymous inner class] this = " + this);
                System.out.println("[annonymous inner class] this.getClass() = " + this.getClass());
            }
        };

        Runnable lambda = () -> {
            //람다에서의 this는 람다가 선언된어 있는 클래스의 인스턴스를 가리킴
            System.out.println("[lambda] this: " + this);
            System.out.println("[lambda] this.class: " + this.getClass());
            System.out.println("[lambda] this.message: " + this.message);
        };

        runnable.run();
        System.out.println("============================");
        lambda.run();
    }

    public static void main(String[] args) {
        OuterMain outerMain = new OuterMain();
        System.out.println("outerMain.message = " + outerMain.message);
        System.out.println("outerMain.message = " + outerMain);
        outerMain.execute();
    }
}
