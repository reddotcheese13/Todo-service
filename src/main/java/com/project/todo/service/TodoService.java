package com.project.todo.service;

import com.project.todo.model.Todo;
import com.project.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Mono<Todo> create(Todo todo) {
        return todoRepository.save(todo);
    }

    public Mono<Todo> findById(String id) {
        return todoRepository.findById(id);
    }

    public Flux<Todo> findByAssignedTo(String assignedTo) {
        return todoRepository.findByAssignedTo(assignedTo);
    }

    public Flux<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Mono<Todo> update(String id, Todo updatedTodo) {
        return todoRepository.findById(id)
                .flatMap(existingTodo -> {
                    existingTodo.setRowDescription(updatedTodo.getRowDescription());
                    existingTodo.setAssignedTo(updatedTodo.getAssignedTo());
                    existingTodo.setDeadline(updatedTodo.getDeadline());
                    existingTodo.setCompleteStatus(updatedTodo.isCompleteStatus());
                    return todoRepository.save(existingTodo);
                });
    }

    public Mono<Void> delete(String id) {
        return todoRepository.deleteById(id);
    }
}
