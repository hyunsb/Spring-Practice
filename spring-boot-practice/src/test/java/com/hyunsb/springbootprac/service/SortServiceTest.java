package com.hyunsb.springbootprac.service;

import com.hyunsb.springbootprac.logic.JavaSort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SortServiceTest {

    private SortService sut;

    @BeforeEach
    void setUp() {
        sut = new SortService(new JavaSort<>());
    }

    @Test
    void doSort_Test() {
        List<String> actual = sut.doSort(List.of("3", "2", "1", "5", "4"));

        assertEquals(List.of("1", "2", "3", "4", "5"), actual);
    }
}