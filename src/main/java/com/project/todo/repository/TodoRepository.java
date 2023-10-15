package com.project.todo.repository;

import com.project.todo.model.Todo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
@Repository
public interface TodoRepository extends ReactiveMongoRepository<Todo, String> {
    Flux<Todo> findByAssignedTo(String assignedTo);
}
