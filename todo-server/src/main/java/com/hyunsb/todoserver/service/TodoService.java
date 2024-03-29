package com.hyunsb.todoserver.service;

import com.hyunsb.todoserver.model.TodoEntity;
import com.hyunsb.todoserver.model.TodoRequest;
import com.hyunsb.todoserver.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoEntity add(TodoRequest request) {
        TodoEntity entity = new TodoEntity(request);
        return todoRepository.save(entity);
    }

    public TodoEntity searchById(Long id) {
        Optional<TodoEntity> result = todoRepository.findById(id);
        return result.orElseThrow(() -> new IllegalArgumentException("아이디에 해당하는 회원 정보가 존재하지 않습니다."));
    }

    @Transactional
    public TodoEntity update(Long id, TodoRequest request) {
        TodoEntity entity = todoRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("아이디에 해당하는 데이터가 존재하지 않습니다.");
        });

        if (!Objects.isNull(request.getTitle())) entity.setTitle(request.getTitle());
        if (!Objects.isNull(request.getOrder())) entity.setOrder(request.getOrder());
        if (!Objects.isNull(request.getCompleted())) entity.setCompleted(request.getCompleted());

        return todoRepository.save(entity);
    }

    public List<TodoEntity> searchAll() {
        return todoRepository.findAll();
    }

    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }

    public void deleteAll() {
        todoRepository.deleteAll();
    }
}
