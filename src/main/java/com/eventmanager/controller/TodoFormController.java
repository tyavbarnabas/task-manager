package com.eventmanager.controller;

import com.eventmanager.model.TodoItem;
import com.eventmanager.repository.TodoItemRepository;
import com.eventmanager.service.ITodoItemService;
import com.eventmanager.service.TodoItemService;
import com.eventmanager.utils.DateAndTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class TodoFormController {
    private final Logger logger = LoggerFactory.getLogger(TodoFormController.class);
    @Autowired
    private TodoItemRepository todoItemRepository;
    @Autowired
    private ITodoItemService todoItemService;
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model){
        TodoItem todoItem = todoItemRepository
                .findById(id)
                .orElseThrow( ()-> new IllegalArgumentException("TodoItem id:" + id + "not found"));
        model.addAttribute("todo",todoItem);
        return "update-todo-item";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") long id,Model model){
        TodoItem todoItem = todoItemRepository
                .findById(id)
                .orElseThrow( ()-> new IllegalArgumentException("TodoItem id:" + id + "not found"));
        model.addAttribute("todo",todoItem);

        todoItemRepository.delete(todoItem);
        return "redirect:/";

    }

    @GetMapping("/create-todo")
    public String showCreateForm(TodoItem todoItem){
        return "add-todo-item";

    }
    @PostMapping("/todo")
    public String createTodoItem(@Valid TodoItem todoItem, BindingResult result, Model model){
        if(result.hasErrors()){
            return "add-todo-item";
        }
        todoItem.setCreatedDate(DateAndTime.getDateAndTime());
        todoItemRepository.save(todoItem);
        return "redirect:/";
    }

    @GetMapping("/view-all")
    public String viewAllTask(Model model, HttpSession  session){
        List<TodoItem> viewAll = todoItemService.viewAllTask((Long) session.getAttribute("id"));
        model.addAttribute("allTask",viewAll);
        return "view-all";
    }

    @GetMapping("/pending")
    public String viewAllPending(Model model){
        List<TodoItem> allPending = todoItemService.getAllPending();
        model.addAttribute("pending",allPending);
        return "pending";
    }

    @GetMapping("/completed")
    public String viewAllCompleted(Model model){
        List<TodoItem>allCompleted = todoItemService.getAllCompleted();
        model.addAttribute( "completed",allCompleted);
        return "completed";
    }

    @GetMapping("/in-progress")
    public String viewInProgress(Model model){
        List<TodoItem> allInProgress = todoItemService.getAllInProgress();
        model.addAttribute("progress",allInProgress);
        return "in-progress";
    }

    @GetMapping ("/add-todo-item")
    public String addATask( Model model){
       model.addAttribute("task",new TodoItem());
        return "addTask";

    }


    @PostMapping ("/add-todo-item")
    public String addATask(@ModelAttribute("task") TodoItem task, Model model , @RequestParam Long id){
         todoItemService.addATask(task , id);
         model.addAttribute("success","Task added successfully");
        return "addTask";

    }




}
