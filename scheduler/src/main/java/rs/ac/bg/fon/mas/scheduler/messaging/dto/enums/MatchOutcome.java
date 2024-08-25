/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.messaging.dto.enums;

/**
 *
 * @author Predrag
 */
public enum MatchOutcome {
    HOME_WIN("Home team won"),
    DRAW("Draw"),
    AWAY_WIN("Away team won");

    private final String displayName;

    MatchOutcome(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
