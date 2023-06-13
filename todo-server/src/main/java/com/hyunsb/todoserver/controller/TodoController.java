package com.hyunsb.todoserver.controller;

import com.hyunsb.todoserver.model.TodoEntity;
import com.hyunsb.todoserver.model.TodoRequest;
import com.hyunsb.todoserver.model.TodoResponse;
import com.hyunsb.todoserver.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/")
    public ResponseEntity<TodoResponse> create(@RequestBody TodoRequest request) {
        System.out.println("create");

        if (Objects.isNull(request.getTitle()))
            return ResponseEntity.badRequest().build();

        TodoEntity result = todoService.add(request);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @GetMapping("/readOne/{id}")
    public ResponseEntity<TodoResponse> readOne(@PathVariable Long id) {
        System.out.println("readOne");
        Optional<TodoEntity> todoEntity = todoService.searchById(id);

        if (todoEntity.isPresent())
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(new TodoResponse(todoEntity.get()));
    }

    @GetMapping("/readAll")
    public ResponseEntity<List<TodoResponse>> readAll() {
        System.out.println("readAll()");
        List<TodoEntity> todoEntities = todoService.searchAll();
        List<TodoResponse> list = todoEntities.stream().map(TodoResponse::new).toList();
        return ResponseEntity.ok(list);
    }

    @PatchMapping("/update")
    public ResponseEntity<TodoResponse> update(@RequestBody Long id, @RequestBody TodoRequest todoRequest) {
        TodoEntity todoEntity = todoService.update(id, todoRequest);
        return ResponseEntity.ok(new TodoResponse(todoEntity));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        todoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAll() {
        todoService.deleteAll();
        return ResponseEntity.ok().build();
    }
}
