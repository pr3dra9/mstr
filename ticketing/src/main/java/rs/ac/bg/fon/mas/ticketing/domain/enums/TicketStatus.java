/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package rs.ac.bg.fon.mas.ticketing.domain.enums;

/**
 *
 * @author Predrag
 */
public enum TicketStatus {
    DRAFT("Draft"),
    ACTIVE("Active"),
    WIN("Win"),
    LOSE("Lose");

    private final String displayName;

    TicketStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    } 
}
