package org.todo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.todo.api.entity.TodoItem;

@Repository
public interface ITodoItemRepository extends JpaRepository<TodoItem, Integer> {
}
