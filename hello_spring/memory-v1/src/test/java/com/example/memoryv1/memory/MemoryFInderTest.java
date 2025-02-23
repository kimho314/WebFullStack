package com.example.memoryv1.memory;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class MemoryFInderTest {

    @Test
    void get() {
        MemoryFinder memoryFinder = new MemoryFinder();
        Memory memory = memoryFinder.get();
        assertThat(memory).isNotNull();
    }
}
