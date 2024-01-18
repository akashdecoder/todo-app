package org.todo.api.dependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.todo.api.dependencycontainer.RepositoryContainer;
//import org.todo.api.repository.IAuthorityRepository;
//import org.todo.api.repository.IRoleRepository;
import org.todo.api.repository.ITodoItemRepository;
import org.todo.api.repository.IUserCredentialRepository;
import org.todo.api.repository.IUserRepository;

@Configuration
public class RepositoryConfig {

//    @Autowired
//    private IAuthorityRepository iAuthorityRepository;
//    @Autowired
//    private IRoleRepository iRoleRepository;
    @Autowired
    private ITodoItemRepository iTodoItemRepository;
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private IUserCredentialRepository iUserCredentialRepository;

    @Bean
    public RepositoryContainer repositoryContainer() {
        return new RepositoryContainer(iTodoItemRepository, iUserRepository, iUserCredentialRepository);
    }
}
