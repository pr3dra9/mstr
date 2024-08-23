/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 *
 * @author Predrag
 */
public class FeedEvent {
    public Integer time;
    public String team;
    public String player;
    public String type;

    @JsonProperty("time")
    private void unpackTime(Map<String, Integer> timeObj) {
        time = timeObj.get("elapsed");
    }

    
    @JsonProperty("team")
    private void unpackTeamName(Map<String, String> teamObj) {
        team = teamObj.get("name");
    }

    @JsonProperty("player")
    private void unpackPlayerName(Map<String, String> playerObj) {
        player = playerObj.get("name");
    }
    
    
    @JsonCreator
    public FeedEvent(//@JsonProperty("time.time") Integer time,
            //@JsonProperty("team.name") String team,
            //@JsonProperty("player.name") String player,
            @JsonProperty("type") String type) {
        //this.time = time;
        //this.team = team;
        //this.player = player;
        this.type = type;
    }

    @Override
    public String toString() {
        return "FeedEvent{" + "time=" + time + ", team=" + team + ", player=" + player + ", type=" + type + '}';
    }
    
}
