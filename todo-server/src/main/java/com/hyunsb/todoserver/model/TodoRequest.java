package com.hyunsb.todoserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TodoRequest {

    public TodoRequest() {
        order = 0L;
        completed = false;
    }

    private String title;
    private Long order;
    private Boolean completed;
}
