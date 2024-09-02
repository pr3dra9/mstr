/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.dto;

/**
 *
 * @author Predrag
 */
public class Away{
    public String name;
    public boolean winner;

    public Away() {
    }

    public Away(String name, boolean winner) {
        this.name = name;
        this.winner = winner;
    }
    
    @Override
    public String toString() {
        return "Away{" + "name=" + name + ", winner=" + winner + '}';
    }

}
