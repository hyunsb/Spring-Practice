package com.hyunsb.todoserver.service;

import com.hyunsb.todoserver.model.TodoEntity;
import com.hyunsb.todoserver.model.TodoRequest;
import com.hyunsb.todoserver.repository.TodoRepository;
import org.apache.coyote.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @Test
    void add() {
        Mockito.when(todoRepository.save(any(TodoEntity.class)))
                .then(AdditionalAnswers.returnsFirstArg());
        TodoRequest expected = new TodoRequest();
        expected.setTitle("Test Title");

        TodoEntity actual = todoService.add(expected);

        Assertions.assertEquals(expected.getTitle(), actual.getTitle());
    }

    @Test
    void searchById() {
        TodoEntity entity = TodoEntity.builder()
                .id(123L)
                .title("Title")
                .order(0L)
                .completed(false).build();

        Optional<TodoEntity> optionalTodoEntity = Optional.of(entity);

        BDDMockito.given(todoRepository.findById(anyLong()))
                .willReturn(optionalTodoEntity);

        TodoEntity expected = optionalTodoEntity.get();
        TodoEntity actual = todoService.searchById(123L);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    void searchById_failed() {
        BDDMockito.given(todoRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            todoService.searchById(123L);
        });
    }
}