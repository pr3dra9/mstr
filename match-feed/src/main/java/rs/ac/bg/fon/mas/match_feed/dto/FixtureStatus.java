/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Predrag
 */
public class FixtureStatus {
    @JsonProperty("long") 
    public String statusLong;
    
    @JsonProperty("short") 
    public String statusShort;
    
    public int elapsed;

    public FixtureStatus() {
    }
    
    public FixtureStatus(String statusLong, String statusShort, int elapsed) {
        this.statusLong = statusLong;
        this.statusShort = statusShort;
        this.elapsed = elapsed;
    }
    
    @Override
    public String toString() {
        return "FixtureStatus{" + "statusLong=" + statusLong + ", statusShort=" + statusShort + ", elapsed=" + elapsed + '}';
    }
    
}
