package com.project.todo.controller;

import com.project.todo.model.Todo;
import com.project.todo.service.TodoService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "Your API Title",
                version = "1.0",
                description = "Your API description"
        )
)
@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public Mono<Todo> create(@RequestBody Todo todo) {
        return todoService.create(todo);
    }

    @GetMapping("/{id}")
    public Mono<Todo> findById(@PathVariable String id) {
        return todoService.findById(id);
    }

    @GetMapping
    public Flux<Todo> findAll() {
        return todoService.findAll();
    }

    @GetMapping("/assignedTo/{assignedTo}")
    public Flux<Todo> findByAssignedTo(@PathVariable String assignedTo) {
        return todoService.findByAssignedTo(assignedTo);
    }

    @PutMapping("/{id}")
    public Mono<Todo> update(@PathVariable String id, @RequestBody Todo updatedTodo) {
        return todoService.update(id, updatedTodo);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return todoService.delete(id);
    }
}
