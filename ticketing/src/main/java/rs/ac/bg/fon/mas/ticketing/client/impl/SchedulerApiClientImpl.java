/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.ticketing.client.impl;

import jakarta.ws.rs.HttpMethod;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import rs.ac.bg.fon.mas.ticketing.client.SchedulerApiClient;
import rs.ac.bg.fon.mas.ticketing.dto.MatchDto;

/**
 *
 * @author Predrag
 */
@Component
public class SchedulerApiClientImpl  implements SchedulerApiClient{

    private static final Logger logger = LoggerFactory.getLogger(SchedulerApiClientImpl.class);

    @Autowired
    RestTemplate restTemplate;
    
    @Value("${api.scheduler.url}")
    String schedulerUrl;

    @Override
    public List<MatchDto> getMatchesByIds(List<Long> ids) {
        String url = UriComponentsBuilder.fromHttpUrl(schedulerUrl)
                .queryParam("ids", ids)
                .toUriString();

        logger.debug(url);
        
        MatchDto[] matchDtos = restTemplate.getForObject(url, MatchDto[].class);
        return matchDtos != null ? List.of(matchDtos) : List.of();
    }
    
}
