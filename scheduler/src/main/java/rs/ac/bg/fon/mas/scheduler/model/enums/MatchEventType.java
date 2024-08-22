/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.model.enums;

/**
 *
 * @author Predrag
 */
public enum MatchEventType {
    GOAL("Goal"),
    YELLOW_CARD("Yellow Card"),
    RED_CARD("Red Card"),
    SUBSTITUTION("Substitution"),
    PENALTY("Penalty"),
    FOUL("Foul"),
    OFFSIDE("Offside"),
    CORNER_KICK("Corner Kick"),
    FREE_KICK("Free Kick"),
    HALFTIME("Halftime"),
    FULLTIME("Full Time");

    private final String displayName;

    MatchEventType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
