package com.coding.test.lambda;

public class Template {
    private void baseOperation(){
        System.out.println("template starts");
    }

    public void execute(int num1, int num2, Operation1 operation){
        baseOperation();

        operation.operation(num1, num2);
    }

    public void execute(Operation2 operation){
        baseOperation();

        operation.operation();
    }
}
