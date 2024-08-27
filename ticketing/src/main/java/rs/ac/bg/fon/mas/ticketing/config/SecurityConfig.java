/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.ticketing.config;

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

    @Autowired
    private Converter<Jwt, Collection<GrantedAuthority>> customJwrConverter;
    
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        //.requestMatchers("/api/v1/**").hasAuthority("SCOPE_profile1")
                        .requestMatchers("/tickets/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .exceptionHandling((handlingSpec) -> handlingSpec
                   .accessDeniedHandler(accessDeniedHandler)
                )
                .csrf((csrf) -> csrf.disable())
                .oauth2ResourceServer((oauth2) -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(customConverter()))
                );
        return http.build();
    }

    private JwtAuthenticationConverter customConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(customJwrConverter);
        return converter;
    }

}
