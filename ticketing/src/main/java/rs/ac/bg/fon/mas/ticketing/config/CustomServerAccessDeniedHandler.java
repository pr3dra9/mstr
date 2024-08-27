/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.ticketing.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author Predrag
 */
@Component
public class CustomServerAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomServerAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, 
             HttpServletResponse response, 
            AccessDeniedException accessDeniedException) throws IOException, ServletException
    {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        logger.error("User: {} attempted to access the protected URL: {}",
            authentication.getName(),
            request.getRequestURI());
        
        authentication.getAuthorities().stream()
            .map(athG -> athG.getAuthority())
            .forEach(authority -> logger.error("Authority: {}", authority));
        
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    
    }
}