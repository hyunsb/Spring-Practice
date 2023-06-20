package com.hyunsb.springbootprac.logic;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface Sort<T extends Comparable<T>> {
    List<T> sort(List<T> list);
}
