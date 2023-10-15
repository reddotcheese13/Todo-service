package com.project.todo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Todo {
    @Id
    private String id;
    private int rowNumber;
    private String rowDescription;
    private String assignedTo;
    private String deadline;
    private boolean completeStatus;
}

