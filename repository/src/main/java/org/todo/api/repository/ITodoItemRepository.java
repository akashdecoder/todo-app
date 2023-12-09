package org.todo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.todo.api.entity.TodoItem;

public interface ITodoItemRepository extends JpaRepository<TodoItem, Integer> {
}
