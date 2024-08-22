/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.auth_server.config;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import rs.ac.bg.fon.mas.auth_server.model.CustomClient;
import rs.ac.bg.fon.mas.auth_server.repo.CustomClientRepository;

/**
 *
 * @author Predrag
 */
@Configuration
public class RegisteredClientRepositoryConfig {
    
    private final CustomClientRepository repo;

    public RegisteredClientRepositoryConfig(CustomClientRepository repo) {
        this.repo = repo;
    }
    
    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        return new RegisteredClientRepository() {
            @Override
            public void save(RegisteredClient registeredClient) {
                CustomClient entityClient = repo
                        .findByClientId(registeredClient.getClientId())
                        .orElse(null);
                if (entityClient != null)
                    return;
                CustomClient customClient = this.toCustomClient(registeredClient);
                repo.save(customClient);
            }

            @Override
            public RegisteredClient findById(String id) {
                RegisteredClient client = repo
                        .findById(Long.valueOf(id))
                        .map(this::toRegisteredClient).orElse(null);
                return client;
            }

            @Override
            public RegisteredClient findByClientId(String clientId) {
                RegisteredClient client = repo
                        .findByClientId(clientId)
                        .map(this::toRegisteredClient).orElse(null);
                return client;
            }
            
            private RegisteredClient toRegisteredClient(CustomClient client) {
                Set<String> scopesSet = client.getScopes();

                Set<AuthorizationGrantType> grantTypesSet = client.getAuthorizationGrantTypes().stream()
                        .map(AuthorizationGrantType::new)
                        .collect(Collectors.toSet());

                ClientSettings clientSettings = ClientSettings.builder()
                        .requireAuthorizationConsent(true)
                        .build();
                
                return RegisteredClient.withId(client.getId().toString())
                        .clientId(client.getClientId())
                        .clientSecret(client.getClientSecret())
                        
                        .scopes(scopes -> scopes.addAll(scopesSet))
                        .redirectUris(ru -> ru.addAll(client.getRedirectUris()))
                        .authorizationGrantTypes(grantTypes -> grantTypes.addAll(grantTypesSet))
                        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                        
                        .clientSettings(clientSettings)
                        .build();   
            }

            private CustomClient toCustomClient(RegisteredClient registeredClient) {
                CustomClient client = new CustomClient();
                
                client.setClientId(registeredClient.getClientId());
                client.setClientSecret(registeredClient.getClientSecret());
                client.setClientName(registeredClient.getClientName());
                
                client.setScopes(registeredClient.getScopes());
                client.setRedirectUris(registeredClient.getRedirectUris());
                client.setAuthorizationGrantTypes(registeredClient.getAuthorizationGrantTypes().stream()
                        .map(AuthorizationGrantType::getValue).collect(Collectors.toSet()));
                
                return null;
            }
            
        };
    }
    
}
