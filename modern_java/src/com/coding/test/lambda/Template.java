package com.coding.test.lambda;

public class Template {
    private void baseOperation(){
        System.out.println("template starts");
    }

    public void template(int num1, int num2, Operation operation){
        baseOperation();

        operation.operation(num1, num2);
    }
}
