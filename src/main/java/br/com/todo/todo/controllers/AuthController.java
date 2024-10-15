package br.com.todo.todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.todo.todo.dtos.UserDTO;
import br.com.todo.todo.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserDTO user) {
        try{
            String token = this.userService.login(user);
            return ResponseEntity.ok(token);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/register")
    public ResponseEntity<Object> create(@RequestBody UserDTO user) {
        try{
            this.userService.create(user);
            return ResponseEntity.ok().body(null);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
        
}
