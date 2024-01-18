package org.todo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.todo.api.entity.UserCredential;
import org.todo.api.repository.IUserCredentialRepository;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserCredentialRepository iUserCredentialRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredential> userCredential =  iUserCredentialRepository.findByUserName(username);
        return userCredential.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}
