package com.hyunsb.todoserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyunsb.todoserver.model.TodoEntity;
import com.hyunsb.todoserver.model.TodoRequest;
import com.hyunsb.todoserver.model.TodoResponse;
import com.hyunsb.todoserver.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TodoService todoService;

    private TodoEntity expected;

    @BeforeEach
    void setUp() {
        expected = TodoEntity.builder()
                .id(123L)
                .title("Test Title")
                .order(0L)
                .completed(false)
                .build();
    }

    @Test
    void create() throws Exception {
        Mockito.when(todoService.add(any(TodoRequest.class)))
                .then(invocation -> {
                    TodoRequest request = invocation.getArgument(0, TodoRequest.class);
                    return new TodoEntity(expected.getId(),
                            request.getTitle(),
                            expected.getOrder(),
                            expected.getCompleted());
                });

        TodoRequest request = new TodoRequest();
        request.setTitle("Any Title");

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(request);

        mvc.perform(MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Any Title"));
    }
}