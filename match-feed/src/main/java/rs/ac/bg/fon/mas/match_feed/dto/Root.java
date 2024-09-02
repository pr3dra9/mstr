/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

/**
 *
 * @author Predrag
 */
public class Root {    
    @JsonProperty("response")
    public ArrayList<Response> response;

    @Override
    public String toString() {
        return "Root{" + "response=" + response + '}';
    }

    public Root() {
    }

    public Root(ArrayList<Response> response) {
        this.response = response;
    }
    
}
