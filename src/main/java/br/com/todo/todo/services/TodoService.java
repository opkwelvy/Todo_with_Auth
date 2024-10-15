package br.com.todo.todo.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Set;

import org.hibernate.id.factory.internal.UUIDGenerationTypeStrategy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.todo.todo.dtos.TodoDTO;
import br.com.todo.todo.models.Todo;
import br.com.todo.todo.models.User;
import br.com.todo.todo.repository.TodoRepository;
import br.com.todo.todo.repository.UserRepository;

@Service
public class TodoService {
    private TodoRepository todoRepository;
    private UserRepository userRepository;

    public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    public Todo create(TodoDTO todo, Object userId) {
        var user = userRepository.findById(UUID.fromString(userId.toString())).orElseThrow(()->{
            throw new RuntimeException("User not found");
        });
        Todo todoBuilded = new Todo(null, todo.getName(), todo.getDescription(), false, todo.getPriority(), new Date(), user);
        todoRepository.save(todoBuilded);
        return todoBuilded;
    }

    public Set<Todo> getAllByUser(UUID userId) {
        var user = userRepository.findById(UUID.fromString(userId.toString())).orElseThrow(()->{
            throw new RuntimeException("User not found");
        });

        Set<Todo> todos = this.todoRepository.findByUser(user).orElseThrow();
        return todos;
    }

    public Set<Todo> update(Todo todo, UUID userId) {
        User user = userRepository.findById(UUID.fromString(userId.toString())).orElseThrow(()->{
            throw new RuntimeException("User not found");
        });
        todo.setUser(user);
        this.todoRepository.save(todo);
        return getAllByUser(userId);
    }

    public Set<Todo> delete(UUID id, UUID userId) {
        this.todoRepository.deleteById(id);
        return getAllByUser(userId);
    }
}
