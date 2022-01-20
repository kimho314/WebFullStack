package com.coding.test.lambda;

public class TemplateTest {
    public static void main(String[] args) {
        Template template = new Template();

        template.template(1, 2, new Operation() {
            @Override
            public void operation(int num1, int num2) {
                int sum = num1 + num2;
                System.out.println("sum : " + sum);
            }
        });
    }
}
