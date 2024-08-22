/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.service_a.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 *
 * @author Predrag
 */
@Component
public class CustomJwtAuthenticationConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public List<GrantedAuthority> convert(Jwt source) {
        var roles = (List<String>) source.getClaim("roles");
        var scopes = (List<String>) source.getClaim("scope");

        List<SimpleGrantedAuthority> rolesList = roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());

        List<SimpleGrantedAuthority> scopesList = scopes
                .stream()
                .map(scope -> new SimpleGrantedAuthority(scope))
                .collect(Collectors.toList());
        
        List<GrantedAuthority> list = new ArrayList<>(rolesList);
        list.addAll(scopesList);
        
        return list;
    }

    @Override
    public <U> Converter<Jwt, U> andThen(Converter<? super Collection<GrantedAuthority>, ? extends U> after) {
        return Converter.super.andThen(after);
    }

}
