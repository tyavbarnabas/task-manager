package com.eventmanager.service;

import com.eventmanager.model.TodoItem;
import org.springframework.scheduling.config.Task;

import java.util.List;

public interface ITodoItemService {
    public List<TodoItem> getAllPending();

    public List<TodoItem> getAllCompleted();
    public List<TodoItem>getAllInProgress();
    public List<TodoItem> viewAllTask(Long id);
    void addATask(TodoItem task , Long id);
}
