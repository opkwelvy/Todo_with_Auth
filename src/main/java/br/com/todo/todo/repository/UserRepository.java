package br.com.todo.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import br.com.todo.todo.models.User;
import java.util.Optional;




public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);    
    Optional<User> findById(UUID id);
} 
