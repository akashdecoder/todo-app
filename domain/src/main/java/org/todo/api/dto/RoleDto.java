package org.todo.api.dto;

import lombok.Data;

import java.util.Set;

@Data
public class RoleDto {
    private String name;
    private Set<AuthorityDto> authorities;
}
