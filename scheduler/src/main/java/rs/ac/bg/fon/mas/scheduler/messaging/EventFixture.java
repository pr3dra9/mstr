/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.messaging;

/**
 *
 * @author Predrag
 */
public class EventFixture {
    private Long fixtureId;
    private String fixtureStatusShort;
    private int fixtureElapsed;

    public EventFixture(Long fixtureId, String fixtureStatusShort, int fixtureElapsed) {
        this.fixtureId = fixtureId;
        this.fixtureStatusShort = fixtureStatusShort;
        this.fixtureElapsed = fixtureElapsed;
    }

    public EventFixture() {
    }
    
    @Override
    public String toString() {
        return "EventFixture{" + "fixtureId=" + fixtureId + ", fixtureStatusShort=" + fixtureStatusShort + ", fixtureElapsed=" + fixtureElapsed + '}';
    }

    public Long getFixtureId() {
        return fixtureId;
    }

    public void setFixtureId(Long fixtureId) {
        this.fixtureId = fixtureId;
    }

    public String getFixtureStatusShort() {
        return fixtureStatusShort;
    }

    public void setFixtureStatusShort(String fixtureStatusShort) {
        this.fixtureStatusShort = fixtureStatusShort;
    }

    public int getFixtureElapsed() {
        return fixtureElapsed;
    }

    public void setFixtureElapsed(int fixtureElapsed) {
        this.fixtureElapsed = fixtureElapsed;
    }
    
}
