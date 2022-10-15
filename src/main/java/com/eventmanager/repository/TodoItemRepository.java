package com.eventmanager.repository;

import com.eventmanager.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TodoItemRepository extends JpaRepository<TodoItem,Long> {
   Optional<List<TodoItem>> findTodoItemsByStatus(String Status);

   @Query(value = "SELECT * FROM todo_item WHERE user_id = ?1", nativeQuery = true)
   List<TodoItem> listOfTaskById(Long user_id);
}
