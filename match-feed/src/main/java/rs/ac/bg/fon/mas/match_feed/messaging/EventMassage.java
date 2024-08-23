/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.messaging;

/**
 *
 * @author Predrag
 */
public class EventMassage {
    public final EventFixture fixture;
    public final EventLeague league;
    public final EventTeams teams;
    public final EventGoals goals;
    public final EventInfo info;

    public EventMassage(EventFixture fixture, EventLeague league, EventTeams teams, EventGoals goals, EventInfo info) {
        this.fixture = fixture;
        this.league = league;
        this.teams = teams;
        this.goals = goals;
        this.info = info;
    }

    @Override
    public String toString() {
        return "EventMassage{" + "fixture=" + fixture + ", league=" + league + ", teams=" + teams + ", goals=" + goals + ", info=" + info + '}';
    }
    
}
