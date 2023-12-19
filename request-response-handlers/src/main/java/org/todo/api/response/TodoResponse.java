package org.todo.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.todo.api.entity.TodoItem;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class TodoResponse {
    private HttpStatus httpStatus;
    private String message;
    private LocalDateTime localDateTime;
    private List<Object> todoItems;
}
