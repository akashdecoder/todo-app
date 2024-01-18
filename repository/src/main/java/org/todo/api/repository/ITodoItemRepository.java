package org.todo.api.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.todo.api.entity.TodoItem;

@Repository
public interface ITodoItemRepository extends MongoRepository<TodoItem, String> {
}
