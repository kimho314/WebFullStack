package com.coding.test.decorator;

public class A3 extends AudiDecorator {
    public A3(ICar audi, String modelName) {
        super(audi, modelName, 2000);
    }
}
