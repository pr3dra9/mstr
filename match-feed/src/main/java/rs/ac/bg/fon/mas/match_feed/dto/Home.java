/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.dto; 

/**
 *
 * @author Predrag
 */
public class Home{
    public String name;
    public boolean winner;

    public Home() {
    }
    
    public Home(String name, boolean winner) {
        this.name = name;
        this.winner = winner;
    }
    
    @Override
    public String toString() {
        return "Home{" + "name=" + name + ", winner=" + winner + '}';
    }
    
}
