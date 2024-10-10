package br.com.todo.todo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.todo.todo.models.Todo;

public interface TodoRepository extends JpaRepository<Todo, UUID> {
}
