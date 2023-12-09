package org.todo.api.dependencycontainer;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.todo.api.repository.IAuthorityRepository;
import org.todo.api.repository.IRoleRepository;
import org.todo.api.repository.ITodoItemRepository;
import org.todo.api.repository.IUserRepository;

@AllArgsConstructor
@Data
public class RepositoryContainer {

    private final IAuthorityRepository iAuthorityRepository;
    private final IRoleRepository iRoleRepository;
    private final ITodoItemRepository iTodoItemRepository;
    private final IUserRepository iUserRepository;
}
