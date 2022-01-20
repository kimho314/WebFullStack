package com.coding.test.lambda;

public class ConcreteOperation implements Operation{

    @Override
    public void operation(int num1, int num2) {
        int sum = num1 + num2;
        System.out.println("sum : " + sum);
    }
}
