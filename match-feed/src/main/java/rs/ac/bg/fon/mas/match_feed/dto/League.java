/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.dto; 

/**
 *
 * @author Predrag
 */
public class League{
    public String name;
    public String country;
    public int season;
    public String round;

    public League() {
    }
    
    public League(String name, String country, int season, String round) {
        this.name = name;
        this.country = country;
        this.season = season;
        this.round = round;
    }
    
    @Override
    public String toString() {
        return "League{" + "name=" + name + ", country=" + country + ", season=" + season + ", round=" + round + '}';
    }
}
