package com.coding.test.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArrayListStreamTest {
    public static void main(String[] args) {
        List<String> sList = new ArrayList<>();
        sList.add("Tomas");
        sList.add("Edward");
        sList.add("Jack");

        sList.stream().forEach(System.out::println);
        System.out.println("================");

        sList.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
        System.out.println("================");

        sList.stream().map(s -> s.length()).forEach(System.out::println);
        System.out.println("================");

        sList.stream().filter(s -> s.length() >= 5).forEach(System.out::println);
    }
}
