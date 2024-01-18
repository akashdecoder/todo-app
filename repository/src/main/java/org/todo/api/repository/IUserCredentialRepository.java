package org.todo.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.todo.api.entity.UserCredential;

import java.util.Optional;

@Repository
public interface IUserCredentialRepository extends MongoRepository<UserCredential, String> {
    Optional<UserCredential> findByUserName(String username);
}
