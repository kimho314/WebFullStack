package com.coding.test.decorator;

public class A5 extends AudiDecorator {
    public A5(ICar audi, String modelName) {
        super(audi, modelName, 4000);
    }
}
