package org.todo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.todo.api.dependencycontainer.RepositoryContainer;
import org.todo.api.dependencycontainer.ServiceContainer;
import org.todo.api.entity.UserCredential;
import org.todo.api.request.UserCredentialRequest;

import java.util.List;

@RestController
@RequestMapping("/todo/auth")
public class SecurityController {

    private final ServiceContainer serviceContainer;
    private final RepositoryContainer repositoryContainer;

    @Autowired
    private AuthenticationManager authenticationManager;

    public SecurityController(ServiceContainer serviceContainer, RepositoryContainer repositoryContainer) {
        this.serviceContainer = serviceContainer;
        this.repositoryContainer = repositoryContainer;
    }

    @PostMapping("/register")
    public String update(@RequestBody UserCredential userCredential) {
        return serviceContainer.getSecurityService().saveUser(userCredential);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody UserCredentialRequest userCredentialRequest) {
        Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userCredentialRequest.getUserName(), userCredentialRequest.getPassword()));
        if(authentication.isAuthenticated()) {
            return serviceContainer.getSecurityService().generateToken(userCredentialRequest.getUserName());
        } else {
            throw new RuntimeException("Invalid Access");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        serviceContainer.getSecurityService().validateToken(token);
        return "Token is valid";
    }

    @GetMapping("/allUsers")
    public List<UserCredential> getUsers() {
        return repositoryContainer.getIUserCredentialRepository().findAll();
    }
}
