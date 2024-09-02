/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.dto; 

/**
 *
 * @author Predrag
 */
public class Teams{
    public Home home;
    public Away away;

    public Teams() {
    }
    
    public Teams(Home home, Away away) {
        this.home = home;
        this.away = away;
    }
    
    @Override
    public String toString() {
        return "Teams{" + "home=" + home + ", away=" + away + '}';
    }
    
}
