/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.auth_server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.mas.auth_server.model.CustomUser;

/**
 *
 * @author Predrag
 */
public interface CustomUserRepository extends JpaRepository<CustomUser, Long>{
    CustomUser findByUsername(String username);
}
