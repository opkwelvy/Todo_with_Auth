package br.com.todo.todo.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.todo.todo.dtos.TodoDTO;
import br.com.todo.todo.models.Todo;
import br.com.todo.todo.repository.TodoRepository;
import br.com.todo.todo.repository.UserRepository;

@Service
public class TodoService {
    private TodoRepository todoRepository;
    private UserRepository userRepository;

    public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> create(TodoDTO todo) {
        var user = userRepository.findById(todo.getUserId()).orElseThrow(()->{
            throw null;
        });
        Todo todoBuilded = new Todo(null, todo.getName(), todo.getDescription(), false, todo.getPriority(), null, user);
        this.todoRepository.save(todoBuilded);
        return getList();
    }

    public List<Todo> getAll(String ordenation) {
        Sort sort;
        if (ordenation == "name") {
            sort = Sort.by("name").descending();
        } else {
            sort = Sort.by("priority").descending();
        }

        return this.todoRepository.findAll(sort);
    }

    public List<Todo> getList() {
        return this.todoRepository.findAll();
    }

    public List<Todo> update(Todo todo) {
        this.todoRepository.save(todo);
        return getList();
    }

    public List<Todo> delete(UUID id) {
        this.todoRepository.deleteById(id);
        return getList();
    }
}
