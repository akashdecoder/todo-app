//package org.todo.api.hibernate;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.todo.api.entity.User;
//import org.todo.api.repository.IUserRepository;
//import org.todo.api.utility.SearchOperations;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class JpaUser {
//
//    @Autowired
//    private IUserRepository userRepository;
//
//    @Autowired
//    private SearchOperations searchOperations;
//
//    public void update(User user) throws Exception {
//        List<User> users = fetch(user.getUserName());
//        try {
//            if(users == null) {
//                userRepository.save(user);
//            } else {
//                throw new Exception("User already exists");
//            }
//        } catch (Exception exception) {
//            throw new Exception(exception.getMessage());
//        }
//
//    }
//
//    public List<User> fetch(String query) {
//        List<Object> foundUsers = null;
//
//        List<Object> users = userRepository.findAll()
//                .stream()
//                .map(user -> (Object) user)
//                .collect(Collectors.toList());
//
//        foundUsers = searchOperations.searchSubstrings(users, User.class, query);
//
//        if(foundUsers == null) {
//            return null;
//        }
//
//        return foundUsers.
//                stream()
//                .map(object -> (User) object)
//                .collect(Collectors.toList());
//    }
//}
