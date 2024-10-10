package br.com.todo.todo.services;

import br.com.todo.todo.dtos.UserDTO;
import br.com.todo.todo.models.User;
import br.com.todo.todo.repository.UserRepository;
import br.com.todo.todo.security.TokenService;
import ch.qos.logback.core.subst.Token;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private TokenService tokenService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService){
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(UserDTO user){
        User userFinded = this.userRepository.findByUsername(user.getUsername()).orElseThrow(()->{
            throw new RuntimeException("Username not found");
        });

        if(passwordEncoder.matches(user.getPassword(), userFinded.getPassword())){
            String token = this.tokenService.generateToken(userFinded);
            return token;
        }else{
            throw new RuntimeException("Username ou Password is wrong");
        }
    }

   

    public void create(UserDTO user){

        Optional<User> userFinded = this.userRepository.findByUsername(user.getUsername());
        if(userFinded.isEmpty()){
            User userBuilded = new User(null, user.getUsername(), passwordEncoder.encode(user.getPassword()), user.getEmail(), null);
            userRepository.save(userBuilded);
        }else{
            throw new RuntimeException("One user was exists with this username or email");
        }

       
    }

    public List<User> getList(){
        Sort sort = Sort.by("username").descending();
        return userRepository.findAll(sort);
    }
    public Optional<User> get(UUID id){
        return userRepository.findById(id);
    }
    public void update(User user){
        userRepository.save(user);
    }
    public void delete(UUID id){
        userRepository.deleteById(id);
    }

}
