/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.service_a.config;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 *
 * @author Predrag
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AccessDeniedHandler accessDeniedHandler;
    private final Converter<Jwt, Collection<GrantedAuthority>> converter;
    
    public SecurityConfig(AccessDeniedHandler accessDeniedHandler, Converter<Jwt, Collection<GrantedAuthority>> converter) {
        this.accessDeniedHandler = accessDeniedHandler;
        this.converter = converter;
    }
    
    
    @Bean
    public SecurityFilterChain web(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authorize) -> authorize
                //.requestMatchers("/test").hasAuthority("USER")
                .requestMatchers("/test").anonymous()
                .anyRequest().authenticated()
            )
            .exceptionHandling((handlingSpec) -> handlingSpec
                .accessDeniedHandler(accessDeniedHandler)
            )
            .oauth2ResourceServer((oauth2) -> oauth2
                .jwt(jwtSpec -> jwtSpec
                    .jwtAuthenticationConverter(customConverter())
                )
            );
        
        return http.build();
    }

    
    private JwtAuthenticationConverter customConverter() {
        JwtAuthenticationConverter jwtAuthConverter = new JwtAuthenticationConverter();
        jwtAuthConverter.setJwtGrantedAuthoritiesConverter(converter);
        return jwtAuthConverter;
    }
    
    
}
