package com.coding.test.lambda;

public class TemplateTest {
    public static void main(String[] args) {
        Template template = new Template();

        template.execute(1, 2, new Operation1() {
            @Override
            public void operation(int num1, int num2) {
                int sum = num1 + num2;
                System.out.println("sum : " + sum);
            }
        });
        System.out.println("==================================");
        template.execute(new Operation2() {
            @Override
            public void operation() {
                System.out.println("do nothing");
            }
        });
    }
}
