package com.coding.test.lambda;

public class AddTest {
    public static void main(String[] args) {
        Add add = (x, y) -> x+y;
        System.out.println(add.add(1,2));
    }
}
