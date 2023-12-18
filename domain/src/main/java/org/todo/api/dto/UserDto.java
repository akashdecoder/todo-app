package org.todo.api.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UserDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private List<TodoItemDto> todoList;
    private String userName;
    private String password;
    private String email;
//    private Set<RoleDto> roles;
}
