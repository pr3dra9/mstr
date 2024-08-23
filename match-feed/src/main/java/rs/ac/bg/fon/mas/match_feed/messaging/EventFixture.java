/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.messaging;

/**
 *
 * @author Predrag
 */
public class EventFixture {
    public final Long fixtureId;
    public final String fixtureStatusShort;
    public final int fixtureElapsed;

    public EventFixture(Long fixtureId, String fixtureStatusShort, int fixtureElapsed) {
        this.fixtureId = fixtureId;
        this.fixtureStatusShort = fixtureStatusShort;
        this.fixtureElapsed = fixtureElapsed;
    }

    @Override
    public String toString() {
        return "EventFixture{" + "fixtureId=" + fixtureId + ", fixtureStatusShort=" + fixtureStatusShort + ", fixtureElapsed=" + fixtureElapsed + '}';
    }
    
}
