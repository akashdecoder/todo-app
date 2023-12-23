package org.todo.api.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.todo.api.entity.User;
import org.todo.api.repository.IUserRepository;
import org.todo.api.utility.SearchOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JpaUser {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private SearchOperations searchOperations;

    public void update(User user) throws Exception {
        User existingUser = null;
        try {
            List<User> users = fetch(user.getUserName());
            if(!users.isEmpty()) {
                if(user.getUserId() != null) {
                    existingUser = userRepository.getReferenceById(user.getUserId());
                    if(existingUser.equals(user)) {
                        throw new Exception("At least update one value.");
                    }
                } else {
                    throw new Exception("Internal Error occurred. Please check the request,");
                }
            }
            userRepository.save(user);
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }

    }

    public List<User> fetch(String query) {
        List<User> allUsers = new ArrayList<>();
        List<Object> foundUsers = null;

        List<Object> users = userRepository.findAll()
                .stream()
                .map(user -> (Object) user)
                .collect(Collectors.toList());

        foundUsers = searchOperations.searchSubstrings(users, User.class, query);

        if(!foundUsers.isEmpty()) {
            allUsers = foundUsers.stream()
                    .map(object -> (User) object)
                    .collect(Collectors.toList());
        }

        return allUsers;
    }
}
