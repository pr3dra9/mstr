/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.service_a.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.mas.service_a.service.TestService;

/**
 *
 * @author Predrag
 */
@Service
public class TestServiceImpl implements TestService{
    
    @Autowired
    private StreamBridge streamBridge;
    
    @Override
    public void sendMessage() {
        streamBridge.send("message-out-0", "TEST");
    }
    
}
