package com.coding.test.lambda;

import java.util.Arrays;

public class IntArrayStreamTest {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};

        Arrays.stream(arr).forEach(System.out::println);
        int sum = Arrays.stream(arr).sum();
        System.out.println(sum);
    }
}
