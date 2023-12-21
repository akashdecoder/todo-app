package org.todo.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.todo.api.entity.TodoItem;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TodoResponse {
    private HttpStatus httpStatus;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    private LocalDateTime localDateTime;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<TodoItem> todoItems;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TodoItem todoItem;

    public TodoResponse(HttpStatus httpStatus, String message, LocalDateTime localDateTime, @Nullable List<TodoItem> todoItems, @Nullable TodoItem todoItem) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.localDateTime = localDateTime;
        if(todoItems != null) {
            this.todoItems = todoItems;
        }
        if(todoItem != null) {
            this.todoItem = todoItem;
        }
    }
}
