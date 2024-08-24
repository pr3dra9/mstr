/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.messaging;

/**
 *
 * @author Predrag
 */
public class EventMassage {
    private EventFixture fixture;
    private EventLeague league;
    private EventTeams teams;
    private EventGoals goals;
    private EventInfo info;

    public EventMassage(EventFixture fixture, EventLeague league, EventTeams teams, EventGoals goals, EventInfo info) {
        this.fixture = fixture;
        this.league = league;
        this.teams = teams;
        this.goals = goals;
        this.info = info;
    }

    public EventMassage() {
    }

    @Override
    public String toString() {
        return "EventMassage{" + "fixture=" + fixture + ", league=" + league + ", teams=" + teams + ", goals=" + goals + ", info=" + info + '}';
    }

    public EventFixture getFixture() {
        return fixture;
    }

    public void setFixture(EventFixture fixture) {
        this.fixture = fixture;
    }

    public EventLeague getLeague() {
        return league;
    }

    public void setLeague(EventLeague league) {
        this.league = league;
    }

    public EventTeams getTeams() {
        return teams;
    }

    public void setTeams(EventTeams teams) {
        this.teams = teams;
    }

    public EventGoals getGoals() {
        return goals;
    }

    public void setGoals(EventGoals goals) {
        this.goals = goals;
    }

    public EventInfo getInfo() {
        return info;
    }

    public void setInfo(EventInfo info) {
        this.info = info;
    }
    
}
