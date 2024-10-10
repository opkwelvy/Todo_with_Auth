package br.com.todo.todo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.todo.todo.dtos.TodoDTO;
import br.com.todo.todo.models.Todo;
import br.com.todo.todo.services.TodoService;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RequestMapping("/todo")
@RestController
public class TodoController {
 
    private TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    // @PostMapping("/")
    // public List<Todo> create(@RequestBody TodoDTO todo) {
    //    return this.todoService.create(todo);
    // }

    @GetMapping("/list")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    @PutMapping("/{id}")
    public List<Todo> putMethodName(@RequestBody Todo todo) {
        return todoService.update(todo);
    }

    @DeleteMapping("/{id}")
    public List<Todo> delete(@PathVariable UUID id){
       return todoService.delete(id);
    }
    
}

