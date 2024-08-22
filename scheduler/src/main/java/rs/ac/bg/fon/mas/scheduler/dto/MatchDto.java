/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.dto;

/**
 *
 * @author Predrag
 */
public record MatchDto (String leagueName, String homeTeam, String awayTeam, 
        String round, String date, String status){}
