/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.messaging;

/**
 *
 * @author Predrag
 */
public class EventInfo {
    public final int time;
    public final String teamName;
    public final String playerName;
    public final String type;

    public EventInfo(int time, String teamName, String playerName, String type) {
        this.time = time;
        this.teamName = teamName;
        this.playerName = playerName;
        this.type = type;
    }

    @Override
    public String toString() {
        return "EventInfo{" + "time=" + time + ", teamName=" + teamName + ", playerName=" + playerName + ", type=" + type + '}';
    }
    
}
