package org.todo.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.todo.api.entity.UserCredential;
import org.todo.api.repository.IUserCredentialRepository;

@Service
public class SecurityService {
    @Autowired
    private IUserCredentialRepository iUserCredentialRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String saveUser(UserCredential userCredential) {
        userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));
        iUserCredentialRepository.save(userCredential);
        return "User Added Successfully";
    }

    public String generateToken(String userName) {
        return jwtService.generateToken(userName);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
