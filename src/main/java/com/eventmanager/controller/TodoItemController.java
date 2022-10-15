package com.eventmanager.controller;

import com.eventmanager.model.Status;
import com.eventmanager.model.TodoItem;
import com.eventmanager.repository.TodoItemRepository;
import com.eventmanager.utils.DateAndTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.Instant;
import java.time.ZoneId;


@Controller
public class TodoItemController {
    private final Logger logger =LoggerFactory.getLogger(TodoItemController.class);
    @Autowired
    private TodoItemRepository todoItemRepository;

    @GetMapping("/home")
    public ModelAndView home(){
        logger.debug("request to GET index");
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("todoItems",todoItemRepository.findAll());
        modelAndView.addObject("today", Instant.now().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek());
        return modelAndView;

    }

    @PostMapping("/todo/{id}")
    public String UpdateTodoItem(@PathVariable("id") long id, @Valid TodoItem todoitem, BindingResult result,Model model){
        if(result.hasErrors()){
            todoitem.setId(id);
            return "update-todo-item";
        }
        if(todoitem.isPending()){
            todoitem.setStatus(Status.INPROGRESS.name());
        todoitem.setUpdatedDate(DateAndTime.getDateAndTime());
        todoItemRepository.save(todoitem);
        return "redirect:/";
        } else if (todoitem.isComplete()) {
            todoitem.setCompletedDate(DateAndTime.getDateAndTime());
            todoitem.setStatus(Status.COMPLETED.name());
            todoItemRepository.save(todoitem);
            return "redirect:/";
        } else {
            todoItemRepository.save(todoitem);
            return "redirect:/";
        }
    }

}
