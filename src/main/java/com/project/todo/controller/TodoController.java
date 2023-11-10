package com.project.todo.controller;

import com.project.todo.model.Todo;
import com.project.todo.service.TodoService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "Todo",
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
    public ResponseEntity<Mono<Todo>> create(@RequestBody Todo todo) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(todoService.create(todo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Todo>> findById(@PathVariable String id) {
        return ResponseEntity.ok(todoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Flux<Todo>> findAll() {
        return ResponseEntity.ok(todoService.findAll());
    }

    @GetMapping("/assignedTo/{assignedTo}")
    public ResponseEntity<Flux<Todo>> findByAssignedTo(@PathVariable String assignedTo) {
        return ResponseEntity.ok(todoService.findByAssignedTo(assignedTo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mono<Todo>> update(@PathVariable String id, @RequestBody Todo updatedTodo) {
        return ResponseEntity.ok(todoService.update(id, updatedTodo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        todoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
