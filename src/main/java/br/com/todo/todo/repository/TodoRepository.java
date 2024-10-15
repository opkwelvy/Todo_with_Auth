package br.com.todo.todo.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.todo.todo.models.Todo;
import br.com.todo.todo.models.User;

import java.util.Set;


public interface TodoRepository extends JpaRepository<Todo, UUID> {
    Optional<Set<Todo>>  findByUser(User user);
}
