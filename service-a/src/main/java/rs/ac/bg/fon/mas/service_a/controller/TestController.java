/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.service_a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.mas.service_a.service.TestService;

/**
 *
 * @author Predrag
 */
@RestController
@RequestMapping("test")
public class TestController {
    
    @Autowired
    TestService service;
    
    @GetMapping("")
    public String get() {
        service.sendMessage();
        return "GET call";
    }

    @PostMapping("")
    public String post() {
        return "POST call";
    }
    
    @PutMapping("")
    public String put() {
        return "PUT call";
    }
    
}
