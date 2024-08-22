/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.auth_server.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Predrag
 */
@Entity
@Table(name = "client")
public class CustomClient {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        
    @Column(name = "client_id")
    private String clientId;
    @Column(name = "secret")
    private String clientSecret;
    @Column(name = "name")
    private String clientName;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name="client_auth_grant_type",
        joinColumns=@JoinColumn(name="client_id")
    )
    private Set<String> authorizationGrantTypes;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name="client_redirect_uri",
        joinColumns=@JoinColumn(name="client_id")
    )
    private Set<String> redirectUris;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name="client_scope",
        joinColumns=@JoinColumn(name="client_id")
    )
    private Set<String> scopes;

    public CustomClient() {
    }

    public CustomClient(Long id, String clientId, String clientSecret, String clientName, Set<String> authorizationGrantTypes, Set<String> redirectUris, Set<String> scopes) {
        this.id = id;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.clientName = clientName;
        this.authorizationGrantTypes = authorizationGrantTypes;
        this.redirectUris = redirectUris;
        this.scopes = scopes;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long Id) {
        this.id = id;
    }
    
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Set<String> getAuthorizationGrantTypes() {
        return authorizationGrantTypes;
    }

    public void setAuthorizationGrantTypes(Set<String> authorizationGrantTypes) {
        this.authorizationGrantTypes = authorizationGrantTypes;
    }

    public Set<String> getRedirectUris() {
        return redirectUris;
    }

    public void setRedirectUris(Set<String> redirectUris) {
        this.redirectUris = redirectUris;
    }

    public Set<String> getScopes() {
        return scopes;
    }

    public void setScopes(Set<String> scopes) {
        this.scopes = scopes;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.clientId);
        hash = 19 * hash + Objects.hashCode(this.clientSecret);
        hash = 19 * hash + Objects.hashCode(this.clientName);
        hash = 19 * hash + Objects.hashCode(this.authorizationGrantTypes);
        hash = 19 * hash + Objects.hashCode(this.redirectUris);
        hash = 19 * hash + Objects.hashCode(this.scopes);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CustomClient other = (CustomClient) obj;
        if (!Objects.equals(this.clientId, other.clientId)) {
            return false;
        }
        if (!Objects.equals(this.clientSecret, other.clientSecret)) {
            return false;
        }
        if (!Objects.equals(this.clientName, other.clientName)) {
            return false;
        }
        if (!Objects.equals(this.authorizationGrantTypes, other.authorizationGrantTypes)) {
            return false;
        }
        if (!Objects.equals(this.redirectUris, other.redirectUris)) {
            return false;
        }
        return Objects.equals(this.scopes, other.scopes);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CustomClient{");
        sb.append("id=").append(id);
        sb.append(", clientId=").append(clientId);
        sb.append(", clientSecret=").append(clientSecret);
        sb.append(", clientName=").append(clientName);
        sb.append(", authorizationGrantTypes=").append(authorizationGrantTypes);
        sb.append(", redirectUris=").append(redirectUris);
        sb.append(", scopes=").append(scopes);
        sb.append('}');
        return sb.toString();
    }
    
}
