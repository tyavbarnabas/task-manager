package com.eventmanager.service;

import com.eventmanager.model.Status;
import com.eventmanager.model.TodoItem;
import com.eventmanager.model.User;
import com.eventmanager.repository.TodoItemRepository;
import com.eventmanager.repository.UserRepository;
import com.eventmanager.utils.DateAndTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoItemService implements ITodoItemService{
    @Autowired
    TodoItemRepository todoItemRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<TodoItem> getAllPending() {
     return todoItemRepository.findTodoItemsByStatus(Status.PENDING.name()).orElseThrow(()->new RuntimeException());

    }
@Override
    public List<TodoItem>getAllCompleted(){
        return  todoItemRepository.findTodoItemsByStatus(Status.COMPLETED.name()).orElseThrow( ()->new RuntimeException());

    }

    @Override
    public List<TodoItem> getAllInProgress() {
        return todoItemRepository.findTodoItemsByStatus(Status.INPROGRESS.name()).orElseThrow(()->new RuntimeException());
    }


    @Override
    public List<TodoItem> viewAllTask(Long id) {
        return  todoItemRepository.listOfTaskById(id);
    }
    @Override
    public void addATask(TodoItem task , Long id) {
        User user = userRepository.findById(id).get();
        task.setUser(user);
        task.setStatus(Status.PENDING.name());
        task.setCreatedDate(DateAndTime.getDateAndTime());
         todoItemRepository.save(task);
    }
}
