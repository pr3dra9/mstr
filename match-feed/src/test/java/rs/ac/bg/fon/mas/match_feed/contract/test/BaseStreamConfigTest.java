/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.contract.test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.MessageChannel;
import reactor.core.publisher.Flux;
import rs.ac.bg.fon.mas.match_feed.dto.Away;
import rs.ac.bg.fon.mas.match_feed.dto.FeedEvent;
import rs.ac.bg.fon.mas.match_feed.dto.Fixture;
import rs.ac.bg.fon.mas.match_feed.dto.FixtureStatus;
import rs.ac.bg.fon.mas.match_feed.dto.Goals;
import rs.ac.bg.fon.mas.match_feed.dto.Home;
import rs.ac.bg.fon.mas.match_feed.dto.League;
import rs.ac.bg.fon.mas.match_feed.dto.Response;
import rs.ac.bg.fon.mas.match_feed.dto.Score;
import rs.ac.bg.fon.mas.match_feed.dto.Teams;
import rs.ac.bg.fon.mas.match_feed.messaging.EventMassage;
import rs.ac.bg.fon.mas.match_feed.service.EventCounterService;
import rs.ac.bg.fon.mas.match_feed.service.FeedService;

/**
 *
 * @author Predrag
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE,
        properties = {"eureka.client.enabled=false", 
            "spring.cloud.config.enabled=false",
            "spring.cloud.stream.bindings.eventSupplier-out-0.destination=matchEvent",
            "spring.cloud.stream.bindings.eventSupplier-out-0.content-type=application/json"})
@AutoConfigureMessageVerifier
@Import(TestChannelBinderConfiguration.class)
public abstract class BaseStreamConfigTest {
    @Autowired
    private Supplier<Flux<EventMassage>> eventSupplier;
    
    @MockBean
    FeedService feedService;
    
    @MockBean
    EventCounterService service;
    
    @Autowired
    @Qualifier("eventSupplier-out-0")
    private MessageChannel output;

    @BeforeEach
    public void setUp() {
        FixtureStatus fixtureStatus = new FixtureStatus("Second Half", "2H", 90);
        Fixture fixture = new Fixture(12345L, "", "", "", fixtureStatus);
        League league = new League("Premier League", "England", 2024, "Regular season - 1");
        Teams teams = new Teams(new Home("Manchester United", false), new Away("Chelsea", false));
        Goals goals = new Goals(2, 1);
        Score score = new Score();        
        ArrayList<FeedEvent> events = new ArrayList<>();
        events.add(new FeedEvent(88, "Manchester United", "Rashford", "Goal"));        
        Response response = new Response(fixture, league, teams, goals, score, events);
        
        Mockito.when(feedService.fetchFeed())
            .thenReturn(List.of(response));

        Mockito.when(feedService.fetchFinished())
            .thenReturn(List.of());

    }
    
    void eventSupplier() {
        Flux<EventMassage> eventMessagesFlux = eventSupplier.get();
        eventMessagesFlux.subscribe(eventMassage -> {
            System.out.println("Received EventMassage: " + eventMassage);
        });
    }
    
}
