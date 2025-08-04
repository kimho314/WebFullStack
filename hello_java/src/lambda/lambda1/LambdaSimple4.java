package lambda.lambda1;

public class LambdaSimple4 {
    public static void main(String[] args) {
        MyCall myCall = (int value) -> value * 2;
        MyCall myCal2 = (value) -> value * 2;
        MyCall myCal3 = value -> value * 2; // 매게변수 1개, () 생략 가능
    }

    interface MyCall {
        int call(int value);
    }
}
