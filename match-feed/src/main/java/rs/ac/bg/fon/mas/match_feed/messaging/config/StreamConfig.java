/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.messaging.config;

import java.util.List;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.function.context.PollableBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import reactor.core.publisher.Flux;
import rs.ac.bg.fon.mas.match_feed.domain.EventCounter;
import rs.ac.bg.fon.mas.match_feed.dto.FeedEvent;
import rs.ac.bg.fon.mas.match_feed.dto.Response;
import rs.ac.bg.fon.mas.match_feed.messaging.EventFixture;
import rs.ac.bg.fon.mas.match_feed.messaging.EventGoals;
import rs.ac.bg.fon.mas.match_feed.messaging.EventInfo;
import rs.ac.bg.fon.mas.match_feed.messaging.EventLeague;
import rs.ac.bg.fon.mas.match_feed.messaging.EventMassage;
import rs.ac.bg.fon.mas.match_feed.messaging.EventTeams;
import rs.ac.bg.fon.mas.match_feed.service.EventCounterService;
import rs.ac.bg.fon.mas.match_feed.service.FeedService;

/**
 *
 * @author Predrag
 */
@Configuration
@Profile("!test")
public class StreamConfig {
    
    private static final Logger logger = LoggerFactory.getLogger(StreamConfig.class);

    FeedService feedService;
    EventCounterService service;

    public StreamConfig(FeedService feedService, EventCounterService service) {
        this.feedService = feedService;
        this.service = service;
    }
    
    @PollableBean
    public Supplier<Flux<EventMassage>> eventSupplier() {
        return () -> {
            Flux<EventMassage> matches =  Flux.fromIterable(feedService.fetchFinished())
                    .flatMap(this::toMatchMessages);
            
            Flux<EventMassage> events =  Flux.fromIterable(feedService.fetchFeed())
                    .flatMap(this::toEventMessages);
            
            return matches.mergeWith(events);
        };
    }
    
    private Flux<EventMassage> toEventMessages(Response response) {
        logger.trace("toEventMessages -> Response: " + response.toString());
        
        int sentEvents = service.getCounter(response.fixture.id);
        int totalEvents = response.events.size();
        
        if (sentEvents >= totalEvents)
            return Flux.empty();
        
        EventFixture fixture = new EventFixture(
                response.fixture.id, 
                response.fixture.status.statusShort, 
                response.fixture.status.elapsed);
        logger.trace("toEventMessages -> EventFixture: " + fixture.toString());
        
        EventLeague league = new EventLeague(
                response.league.name, 
                response.league.country, 
                response.league.round);
        logger.trace("toEventMessages -> EventLeague: " + league.toString());
        
        EventTeams teams = new EventTeams(
                response.teams.home.name, 
                response.teams.away.name);
        logger.trace("toEventMessages -> EventTeams: " + teams.toString());
        
        EventGoals goals = new EventGoals(
                response.goals.home, 
                response.goals.away);
        logger.trace("toEventMessages -> EventGoals: " + goals.toString());
        
        List<FeedEvent> newEvents = response.events.subList(sentEvents, totalEvents);
        logger.trace("toEventMessages -> newEvents: " + newEvents.toString());
        
        Flux<EventMassage> eventMassages = Flux.fromIterable(newEvents)
                .map(event -> new EventMassage(
                        fixture, 
                        league, 
                        teams, 
                        goals,
                        new EventInfo(event.time, event.team, event.player,event.type))
                );
        
        service.saveEventCounter(new EventCounter(response.fixture.id, totalEvents));
        
        return eventMassages;
    }
    
    private Flux<EventMassage> toMatchMessages(Response response) {
        logger.trace("toMatchMessages -> Response: " + response.toString());

        if (service.isFinished(response.fixture.id)) {
            return Flux.empty();
        }

        EventFixture fixture = new EventFixture(
                response.fixture.id,
                response.fixture.status.statusShort,
                response.fixture.status.elapsed);
        logger.trace("toMatchMessages -> MsgFixture: " + fixture.toString());

        EventLeague league = new EventLeague(
                response.league.name,
                response.league.country,
                response.league.round);
        logger.trace("toMatchMessages -> MsgLeague: " + league.toString());

        EventTeams teams = new EventTeams(
                response.teams.home.name,
                response.teams.away.name);
        logger.trace("toMatchMessages -> MsgTeams: " + teams.toString());

        EventGoals goals = new EventGoals(
                response.goals.home,
                response.goals.away);
        logger.trace("toMatchMessages -> MsgGoals: " + goals.toString());

        Flux<EventMassage> matchMessage = Flux.just(
                new EventMassage(
                        fixture,
                        league,
                        teams,
                        goals
                        ,null));

        service.setFinished(response.fixture.id);

        return matchMessage;
    }

}
