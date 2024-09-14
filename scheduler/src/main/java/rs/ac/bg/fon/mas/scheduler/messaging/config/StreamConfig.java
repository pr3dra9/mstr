/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.messaging.config;

import java.util.List;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import reactor.core.publisher.Flux;
import rs.ac.bg.fon.mas.scheduler.messaging.dto.EventInfo;
import rs.ac.bg.fon.mas.scheduler.messaging.dto.EventMassage;
import rs.ac.bg.fon.mas.scheduler.model.Match;
import rs.ac.bg.fon.mas.scheduler.model.MatchEvent;
import rs.ac.bg.fon.mas.scheduler.model.enums.MatchEventType;
import rs.ac.bg.fon.mas.scheduler.model.enums.MatchStatus;
import rs.ac.bg.fon.mas.scheduler.service.MatchEventService;
import rs.ac.bg.fon.mas.scheduler.service.MatchService;

/**
 *
 * @author Predrag
 */
@Configuration
@Profile("!test")
public class StreamConfig {

    private static final Logger logger = LoggerFactory.getLogger(StreamConfig.class);

    @Autowired
    MatchService matchService;

    @Autowired
    MatchEventService eventService;

    @Bean
    public Consumer<Flux<EventMassage>> eventConsumer() {
        return flux -> flux
                .doOnNext(this::processMessage)
                .subscribe();
    }

    private void processMessage(EventMassage eventMssg) {
        logger.trace("processMessage: " + eventMssg.toString());

        Match match = matchService.findMatch(
                eventMssg.getLeague().getCountry(),
                eventMssg.getLeague().getName(),
                eventMssg.getLeague().getRound(),
                eventMssg.getTeams().getHome());

        if (match == null || match.getStatus() == MatchStatus.COMPLETED) {
            return;
        }

        processMatch(match, eventMssg);
        processMatchEvent(match, eventMssg);
    }

    private void processMatch(Match match, EventMassage eventMssg) {
        logger.trace("Processing match: {}", match);
        
        match.setAwayTeamGoals(eventMssg.getGoals().getAway());
        match.setHomeTeamGoals(eventMssg.getGoals().getHome());

        if (eventMssg.getFixture().getFixtureStatusShort().equalsIgnoreCase("FT")) {
            match = matchService.toComplited(match);
            return;
        }
        
        if (match.getStatus() == MatchStatus.SCHEDULED) {
            match.setStatus(MatchStatus.IN_PROGRESS);
        } 

        match = matchService.update(match.getId(), match);

    }
    
    private void processMatchEvent(Match match, EventMassage eventMssg) {
        EventInfo info = eventMssg.getInfo();

        if (List.of("ft", "aet", "pen", "FT", "AET", "PEN").contains(eventMssg.getFixture().getFixtureStatusShort()))
            return;
        
        MatchEvent event = new MatchEvent(
                match,
                toMatchEventType(info.getType()),
                match.getHomeTeam().getName().equalsIgnoreCase(info.getTeamName()) ? match.getHomeTeam() : match.getAwayTeam(),
                info.getPlayerName(),
                info.getTime(),
                "");
        
        eventService.create(event);
        logger.trace("Event processed: {}", event.toString());
    }

    private MatchEventType toMatchEventType(String type) {
        return switch (type) {
            case "Goal" ->
                MatchEventType.GOAL;
            case "Card", "Yellow Card" ->
                MatchEventType.YELLOW_CARD;
            case "Subst", "Substitution", "subst" ->
                MatchEventType.SUBSTITUTION;
            case "Var" ->
                MatchEventType.VAR;
            case "Red Card" ->
                MatchEventType.RED_CARD;
            case "Penalty" ->
                MatchEventType.PENALTY;
            case "Foul" ->
                MatchEventType.FOUL;
            case "Offside" ->
                MatchEventType.OFFSIDE;
            case "Corner Kick" ->
                MatchEventType.CORNER_KICK;
            case "Free Kick" ->
                MatchEventType.FREE_KICK;
            case "Halftime" ->
                MatchEventType.HALFTIME;
            case "Full Time" ->
                MatchEventType.FULLTIME;
            default ->
                MatchEventType.UNKNOWN;
        };
    }
    
}
