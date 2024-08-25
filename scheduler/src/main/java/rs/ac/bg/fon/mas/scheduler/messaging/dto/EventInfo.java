/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.messaging.dto;

/**
 *
 * @author Predrag
 */
public class EventInfo {
    private int time;
    private String teamName;
    private String playerName;
    private String type;

    public EventInfo(int time, String teamName, String playerName, String type) {
        this.time = time;
        this.teamName = teamName;
        this.playerName = playerName;
        this.type = type;
    }

    public EventInfo() {
    }

    @Override
    public String toString() {
        return "EventInfo{" + "time=" + time + ", teamName=" + teamName + ", playerName=" + playerName + ", type=" + type + '}';
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
