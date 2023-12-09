package org.todo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.todo.api.entity.User;

public interface IUserRepository extends JpaRepository<User, Integer> {
}
