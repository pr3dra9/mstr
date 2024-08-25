/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.ticketing.messaging.config;

import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import rs.ac.bg.fon.mas.ticketing.domain.enums.PredictionOutcome;
import rs.ac.bg.fon.mas.ticketing.messaging.dto.MatchMassage;
import rs.ac.bg.fon.mas.ticketing.messaging.dto.enums.MatchOutcome;
import rs.ac.bg.fon.mas.ticketing.service.PredictionService;
import rs.ac.bg.fon.mas.ticketing.service.TicketService;

/**
 *
 * @author Predrag
 */
@Configuration
@Profile("!test")
public class StreamConfig {

    private static final Logger logger = LoggerFactory.getLogger(StreamConfig.class);
    
    @Autowired
    TicketService ticketService;

    @Autowired
    PredictionService predictionService;
    
    @Bean
    public Consumer<MatchMassage> eventConsumer() {
        return this::processMessage;
    }
        
    private void processMessage(MatchMassage message) {
        logger.trace("processMessage: " + message.toString());
        predictionService.changeStatus(message.getMatchId(), toPredictionOutcome(message.getOutcome()));
        ticketService.checkTickets(message.getMatchId());
    }
    
    private PredictionOutcome toPredictionOutcome(MatchOutcome matchOutcome) {
        switch (matchOutcome) {
            case MatchOutcome.AWAY_WIN -> {
                return PredictionOutcome.AWAY_WIN;
            }
            case MatchOutcome.DRAW -> {
                return PredictionOutcome.DRAW;
            }
            case MatchOutcome.HOME_WIN -> {
                return PredictionOutcome.HOME_WIN;
            }
            default -> throw new AssertionError();
        }
    }
    
}
