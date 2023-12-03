package com.project.todo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document
public class Todo {
    @Id
    private String id;
    private int rowNumber;
    private String rowDescription;
    private String assignedTo;
    private LocalDate deadline;
    private boolean completeStatus;
}

