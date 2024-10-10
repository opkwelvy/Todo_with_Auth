package br.com.todo.todo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import br.com.todo.todo.dtos.UserDTO;
import br.com.todo.todo.models.User;
import br.com.todo.todo.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<Object> create(@RequestBody UserDTO user) {
        try{
            userService.create(user);
            return ResponseEntity.ok().body(null);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
    @GetMapping("/list")
    public ResponseEntity<Object> getList(){
        try{
            var users = userService.getList();
            return ResponseEntity.ok().body(users);

        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
  
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable UUID id) {
        try{
            var profile = userService.get(id);
            return ResponseEntity.ok().body(profile);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody User user) {
        try{
            userService.update(user);
            return ResponseEntity.ok().body(null);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}") ResponseEntity<Object> delete(@PathVariable UUID id){
        try{
            userService.delete(id);
            return ResponseEntity.ok().body(null);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
    
}