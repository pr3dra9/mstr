/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.ticketing.dto;

/**
 *
 * @author Predrag
 */
public record MatchDto(
    Long id,
    MatchLeagueDto league,
    MatchTeamDto homeTeam,
    MatchTeamDto awayTeam,
    String round,
    String date,
    String status,
    int homeTeamGoals,
    int awayTeamGoals) {
}
