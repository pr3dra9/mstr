/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 *
 * @author Predrag
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception {
        http
            .authorizeExchange((authorize) -> authorize
                .pathMatchers("/login").permitAll()
                .pathMatchers("/oauth2/**").permitAll()
                .pathMatchers("/api/v1/scheduler/matches/**").permitAll()
                .anyExchange().authenticated()
            )
            
            .csrf((csrf) -> csrf.disable())
            
            .formLogin(customizer -> customizer
                    .disable())
            
            .oauth2ResourceServer((oauth2) -> oauth2
                    .jwt(Customizer.withDefaults()));
        
        return http.build();
    }

}
