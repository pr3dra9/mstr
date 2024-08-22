/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.mas.auth_server.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.mas.auth_server.model.CustomClient;

/**
 *
 * @author Predrag
 */
public interface CustomClientRepository extends JpaRepository<CustomClient, Long>{
    Optional<CustomClient> findByClientId(String clientId);
}
