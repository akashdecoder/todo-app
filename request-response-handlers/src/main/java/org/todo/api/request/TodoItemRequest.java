package org.todo.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.todo.api.entity.TodoItem;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoItemRequest {

    private String q;
    private TodoItem user;
}
