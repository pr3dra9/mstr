/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.dto;

/**
 *
 * @author Predrag
 */
public class Goals{
    public int home;
    public int away;

    public Goals() {
    }

    public Goals(int home, int away) {
        this.home = home;
        this.away = away;
    }
    
    @Override
    public String toString() {
        return "Goals{" + "home=" + home + ", away=" + away + '}';
    }

}
