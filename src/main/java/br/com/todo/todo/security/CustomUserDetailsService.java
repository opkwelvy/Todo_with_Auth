package br.com.todo.todo.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.todo.todo.models.User;
import br.com.todo.todo.repository.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = this.userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
       return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList());
    }
    
}
