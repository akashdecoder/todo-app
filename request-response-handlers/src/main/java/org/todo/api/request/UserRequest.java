package org.todo.api.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.todo.api.entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String q;
    private String userName;
    private String userEmail;
    private User user;
}
