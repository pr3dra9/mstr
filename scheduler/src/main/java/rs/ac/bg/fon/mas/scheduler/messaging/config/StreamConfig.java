/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.messaging.config;

import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import reactor.core.publisher.Flux;
import rs.ac.bg.fon.mas.scheduler.messaging.EventInfo;
import rs.ac.bg.fon.mas.scheduler.messaging.EventMassage;
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

        if (match == null) {
            return;
        }

        logger.trace("processMessage: " + match.toString());

        if (match.getStatus() != MatchStatus.COMPLETED && match.getStatus() != MatchStatus.IN_PROGRESS) {
            match.setStatus(MatchStatus.IN_PROGRESS);
            if (eventMssg.getFixture().getFixtureStatusShort().equalsIgnoreCase("FT")) {
                match.setStatus(MatchStatus.COMPLETED);
            }
            match = matchService.update(match);
        }

        EventInfo info = eventMssg.getInfo();

        MatchEvent event = new MatchEvent(
                match,
                toMatchEventType(info.getType()),
                match.getHomeTeam().getName().equalsIgnoreCase(info.getTeamName()) ? match.getHomeTeam() : match.getAwayTeam(),
                info.getPlayerName(),
                info.getTime(),
                "");
        logger.trace("processMessage: " + event.toString());
        eventService.create(event);
    }

    private MatchEventType toMatchEventType(String type) {
        return switch (type) {
            case "Goal" ->
                MatchEventType.GOAL;
            case "Card" ->
                MatchEventType.YELLOW_CARD;
            case "Subst" ->
                MatchEventType.SUBSTITUTION;
            case "Var" ->
                MatchEventType.VAR;
            default ->
                null;
        };
    }

}
