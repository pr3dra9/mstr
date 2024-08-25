/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.mas.ticketing.client;

import java.util.List;
import rs.ac.bg.fon.mas.ticketing.dto.MatchDto;

/**
 *
 * @author Predrag
 */
public interface SchedulerApiClient {
    List<MatchDto> getMatchesByIds(List<Long> ids);
}
