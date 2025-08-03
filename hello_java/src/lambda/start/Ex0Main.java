package lambda.start;

import java.util.function.Consumer;

public class Ex0Main {
    private static Consumer<String> FUNC = (string) -> {
        System.out.println("프로그램 시작");
        System.out.println("Hello " + string);
        System.out.println("프로그램 종료");
    };

    public static void main(String[] args) {
        FUNC.accept("Java");
        FUNC.accept("Spring");
    }
}
