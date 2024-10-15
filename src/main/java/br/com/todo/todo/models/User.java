package br.com.todo.todo.models;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String username;
    private String password;
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonManagedReference
    
    private Set<Todo> todos = new HashSet<>();

    public void addTodo(Set<Todo> todos){
        this.todos = todos;
    }

    public void removeTodo(Todo todo){
        todos.remove(todo);
        todo.setUser(null);
    }
}
