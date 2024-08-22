/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.auth_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import rs.ac.bg.fon.mas.auth_server.repo.CustomUserRepository;

/**
 *
 * @author Predrag
 */
@Configuration
public class UserDetailsServiceConfig {
    private final CustomUserRepository repo;

    public UserDetailsServiceConfig(CustomUserRepository repo) {
        this.repo = repo;
    }
    
    @Bean
    public UserDetailsService userService() {
        return (username) -> {
            UserDetails user = repo.findByUsername(username);
            System.out.println(user);
            return user;
        };
    }
    
}
