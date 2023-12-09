package org.todo.api.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class TodoItemDto {
    private Integer id;
    private Integer userId;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
}
