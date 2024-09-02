/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.dto; 

/**
 *
 * @author Predrag
 */
public class Fulltime{
    public Object home;
    public Object away;

    public Fulltime() {
    }

    public Fulltime(Object home, Object away) {
        this.home = home;
        this.away = away;
    }
    
    @Override
    public String toString() {
        return "Fulltime{" + "home=" + home + ", away=" + away + '}';
    }
}
