package br.com.todo.todo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.todo.todo.dtos.TodoDTO;
import br.com.todo.todo.models.Todo;
import br.com.todo.todo.services.TodoService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Set;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/todo")
public class TodoController {
 
    private TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody TodoDTO todo, HttpServletRequest request) {
        var userId = request.getAttribute("userId");
        try{
             Todo todoBuilded = this.todoService.create(todo, userId);
            return ResponseEntity.ok().body(todoBuilded);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<Object> getListTodoByUser(HttpServletRequest request) {
        UUID userId = UUID.fromString(request.getAttribute("userId").toString());
        try{
            Set<Todo> todos = this.todoService.getAllByUser(userId);
            return ResponseEntity.ok().body(todos);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping()
    public ResponseEntity<Object> putMethodName(@RequestBody Todo todo, HttpServletRequest request) {
        UUID userId = UUID.fromString(request.getAttribute("userId").toString());
        try{
            Set<Todo> todos = todoService.update(todo, userId);
            return ResponseEntity.ok().body(todos);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id, HttpServletRequest request){
        UUID userId = UUID.fromString(request.getAttribute("userId").toString());

        try{
             Set<Todo> todos = todoService.delete(id, userId);
             return ResponseEntity.ok().body(todos);

        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}

