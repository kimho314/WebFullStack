package com.coding.test.decorator;

public class A4 extends AudiDecorator {
    public A4(ICar audi, String modelName) {
        super(audi, modelName, 3000);
    }
}
