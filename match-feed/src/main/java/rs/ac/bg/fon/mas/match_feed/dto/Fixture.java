/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 *
 * @author Predrag
 */
public class Fixture {

    public Long id;
    public String timezone;
    public String date;
    
    public String venueName;
    public FixtureStatus status;

    @JsonProperty("venue")
    private void unpackNameFromNestedObject(Map<String, String> venue) {
        venueName = venue.get("name");
    }

    public Fixture() {
    }
    
    public Fixture(Long id, String timezone, String date, String venueName, FixtureStatus status) {
        this.id = id;
        this.timezone = timezone;
        this.date = date;
        this.venueName = venueName;
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "Fixture{" + "id=" + id + ", timezone=" + timezone + ", date=" + date + ", venueName=" + venueName + ", status=" + status + '}';
    }
    
}
