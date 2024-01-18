package org.todo.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.todo.api.entity.User;

@Repository
public interface IUserRepository extends MongoRepository<User, String> {

}
