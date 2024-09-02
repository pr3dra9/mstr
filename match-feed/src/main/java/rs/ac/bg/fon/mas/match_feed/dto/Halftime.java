/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.dto; 

/**
 *
 * @author Predrag
 */
public class Halftime{
    public int home;
    public int away;

    public Halftime() {
    }

    public Halftime(int home, int away) {
        this.home = home;
        this.away = away;
    }
    
    @Override
    public String toString() {
        return "Halftime{" + "home=" + home + ", away=" + away + '}';
    }

}
