/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.dto;

import java.util.ArrayList;

/**
 *
 * @author Predrag
 */
public class Response {
    public Fixture fixture;
    public League league;
    public Teams teams;
    public Goals goals;
    public Score score;
    public ArrayList<FeedEvent> events;

    public Response() {
    }

    public Response(Fixture fixture, League league, Teams teams, Goals goals, Score score, ArrayList<FeedEvent> events) {
        this.fixture = fixture;
        this.league = league;
        this.teams = teams;
        this.goals = goals;
        this.score = score;
        this.events = events;
    }

    @Override
    public String toString() {
        return "Response{" + "fixture=" + fixture + ", league=" + league + ", teams=" + teams + ", goals=" + goals + ", score=" + score + ", events=" + events + '}';
    }
    
}
