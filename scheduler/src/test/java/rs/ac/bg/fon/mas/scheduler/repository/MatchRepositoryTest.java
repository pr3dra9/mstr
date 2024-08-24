/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.scheduler.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import rs.ac.bg.fon.mas.scheduler.model.League;
import rs.ac.bg.fon.mas.scheduler.model.Match;
import rs.ac.bg.fon.mas.scheduler.model.Team;
import rs.ac.bg.fon.mas.scheduler.model.enums.MatchStatus;

/**
 *
 * @author Predrag
 */
@SpringBootTest(properties = {"eureka.client.enabled=false", "spring.cloud.config.enabled=false"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class MatchRepositoryTest {

    @Autowired
    MatchRepository matchRepo;

    @Autowired
    LeagueRepository leagueRepo;

    @Autowired
    TeamRepository teamRepo;

    private Team arsenalEntity;
    private Team chelseaEntity;
    private League leagueEntity;

    @BeforeAll
    public void setUp() {
        Team arsenal = new Team(0L, "Arsenal", "ars.png", "England", "London", "Emirates");
        arsenalEntity = teamRepo.save(arsenal);

        Team chelsea = new Team(0L, "Chelsea", "che.png", "England", "London", "Stamford Bridge");
        chelseaEntity = teamRepo.save(chelsea);

        League league = new League(0L, "England", 1, "2024-25", "Premier League", "pl.png", 38, Set.of(arsenalEntity, chelseaEntity));
        leagueEntity = leagueRepo.save(league);

    }

    @AfterAll
    public void tearDownAll() {
        leagueRepo.delete(leagueEntity);
        teamRepo.delete(chelseaEntity);
        teamRepo.delete(arsenalEntity);
    }

    @Test
    public void testMatchCreate() {
        Match match = new Match(leagueEntity, arsenalEntity, chelseaEntity, "1", LocalDateTime.parse("2024-08-21T21:00:00"), MatchStatus.SCHEDULED);

        Match savedMatch = null;
        try {
            savedMatch = matchRepo.save(match);

            assertNotNull(savedMatch.getId(), "Match ID should not be null");
            assertEquals(leagueEntity, savedMatch.getLeague());
            assertEquals(arsenalEntity, savedMatch.getHomeTeam(), "Team should be Arsenal");
            assertEquals(chelseaEntity, savedMatch.getAwayTeam(), "Team should be Chelsea");
            assertEquals("1", savedMatch.getRound(), "Round should be 1");
            assertEquals(LocalDateTime.parse("2024-08-21T21:00:00"), savedMatch.getDate());
            assertEquals(MatchStatus.SCHEDULED, savedMatch.getStatus(), "EventType should be SCHEDULED");

        } finally {
            if (savedMatch != null) {
                matchRepo.delete(savedMatch);
            }
        }
    }

    @Test
    public void testMatchFindById() {
        Match match = new Match(leagueEntity, arsenalEntity, chelseaEntity, "1", LocalDateTime.parse("2024-08-21T21:00:00"), MatchStatus.SCHEDULED);

        Match savedMatch = null;
        try {
            savedMatch = matchRepo.save(match);
            assertNotNull(savedMatch);
            assertNotNull(savedMatch.getId());

            savedMatch = matchRepo.findById(savedMatch.getId()).orElse(null);

            assertNotNull(savedMatch);
            assertNotNull(savedMatch.getId(), "Match ID should not be null");
            assertEquals(leagueEntity, savedMatch.getLeague());
            assertEquals(arsenalEntity, savedMatch.getHomeTeam(), "Team should be Arsenal");
            assertEquals(chelseaEntity, savedMatch.getAwayTeam(), "Team should be Chelsea");
            assertEquals("1", savedMatch.getRound(), "Round should be 1");
            assertEquals(LocalDateTime.parse("2024-08-21T21:00:00"), savedMatch.getDate());
            assertEquals(MatchStatus.SCHEDULED, savedMatch.getStatus(), "EventType should be SCHEDULED");

        } finally {
            if (savedMatch != null) {
                matchRepo.delete(savedMatch);
            }
        }
    }

    @Test
    public void testFindMatchesBetweenDates() {
        Match match1 = new Match(leagueEntity, arsenalEntity, chelseaEntity, "1", LocalDateTime.parse("2024-08-21T00:00:00"), MatchStatus.SCHEDULED);
        Match match2 = new Match(leagueEntity, chelseaEntity, arsenalEntity, "2", LocalDateTime.parse("2024-08-21T15:00:00"), MatchStatus.SCHEDULED);
        Match match3 = new Match(leagueEntity, arsenalEntity, chelseaEntity, "3", LocalDateTime.parse("2024-08-22T00:00:00"), MatchStatus.SCHEDULED);

        List<Match> savedMatches = null;
        try {
            savedMatches = matchRepo.saveAll(List.of(match1, match2, match3));
            assertNotNull(savedMatches);
            assertEquals(3, savedMatches.size());

            List<Match> sameDayMatches = matchRepo.findMatchesBetweenDates(LocalDateTime.parse("2024-08-21T00:00:00"), LocalDateTime.parse("2024-08-22T00:00:00"));

            assertEquals(2, sameDayMatches.size());

        } finally {
            if (savedMatches != null) {
                matchRepo.deleteAll(savedMatches);
            }
        }
    }

    @Test
    public void testFindByLeagueAndHomeTeamAndAwayTeamAndRound() {
        Match match = new Match(leagueEntity, arsenalEntity, chelseaEntity, "1", LocalDateTime.parse("2024-08-21T00:00:00"), MatchStatus.SCHEDULED);
        Match savedMatche = null;

        try {
            savedMatche = matchRepo.save(match);
            assertNotNull(savedMatche);

            Match dbMatch = matchRepo
                    .findByLeagueAndHomeTeamAndAwayTeamAndRound(leagueEntity, arsenalEntity, chelseaEntity, "1")
                    .orElse(null);

            assertNotNull(dbMatch);
        } finally {
            if (savedMatche != null) {
                matchRepo.delete(savedMatche);
            }
        }
    }

    @Test
    public void testGetByIds() {
        Match match1 = new Match(leagueEntity, arsenalEntity, chelseaEntity, "1", LocalDateTime.parse("2024-08-21T00:00:00"), MatchStatus.SCHEDULED);
        Match match2 = new Match(leagueEntity, chelseaEntity, arsenalEntity, "2", LocalDateTime.parse("2024-08-21T15:00:00"), MatchStatus.SCHEDULED);
        Match match3 = new Match(leagueEntity, arsenalEntity, chelseaEntity, "3", LocalDateTime.parse("2024-08-22T00:00:00"), MatchStatus.SCHEDULED);

        List<Long> ids = new ArrayList<>();
        try {
            Match m1 = matchRepo.save(match1);
            ids.add(m1.getId());
            Match m2 = matchRepo.save(match2);
            ids.add(m2.getId());
            Match m3 = matchRepo.save(match3);
            ids.add(m3.getId());

            List<Match> entityMatches = matchRepo.findByIds(ids);

            assertEquals(3, entityMatches.size());

        } finally {
            if (!ids.isEmpty()) {
                for (Long id : ids) {
                    matchRepo.deleteById(id);
                }

            }
        }
    }

    @Test
    public void testMatchUpdate() {
        Match match = new Match(leagueEntity, arsenalEntity, chelseaEntity, "1", LocalDateTime.parse("2024-08-21T21:00:00"), MatchStatus.SCHEDULED);

        Match savedMatch = null;
        try {
            savedMatch = matchRepo.save(match);
            assertNotNull(savedMatch);
            assertNotNull(savedMatch.getId());

            savedMatch.setDate(LocalDateTime.parse("2024-08-22T21:00:00"));
            savedMatch.setStatus(MatchStatus.IN_PROGRESS);
            savedMatch.setRound("2");
            savedMatch.setHomeTeam(chelseaEntity);
            savedMatch.setAwayTeam(arsenalEntity);

            savedMatch = matchRepo.save(savedMatch);

            assertNotNull(savedMatch);
            assertNotNull(savedMatch.getId(), "Match ID should not be null");
            assertEquals(leagueEntity, savedMatch.getLeague());
            assertEquals(chelseaEntity, savedMatch.getHomeTeam(), "Team should be Arsenal");
            assertEquals(arsenalEntity, savedMatch.getAwayTeam(), "Team should be Chelsea");
            assertEquals("2", savedMatch.getRound(), "Round should be 1");
            assertEquals(LocalDateTime.parse("2024-08-22T21:00:00"), savedMatch.getDate());
            assertEquals(MatchStatus.IN_PROGRESS, savedMatch.getStatus(), "EventType should be SCHEDULED");

        } finally {
            if (savedMatch != null) {
                matchRepo.delete(savedMatch);
            }
        }
    }

    @Test
    public void testMatchDelete() {
        Match match = new Match(leagueEntity, arsenalEntity, chelseaEntity, "1", LocalDateTime.parse("2024-08-21T21:00:00"), MatchStatus.SCHEDULED);

        Match savedMatch = null;
        try {
            savedMatch = matchRepo.save(match);

            assertNotNull(savedMatch.getId(), "Match ID should not be null");

            matchRepo.deleteById(savedMatch.getId());

            savedMatch = matchRepo.findById(savedMatch.getId()).orElse(null);

            assertNull(savedMatch);

        } finally {
            if (savedMatch != null) {
                matchRepo.delete(savedMatch);
            }
        }
    }

    @Test
    public void testFindBy_LeagueRegion_And_LeagueName_And_Round_And_HomeTeamName() {
        Match match = new Match(leagueEntity, arsenalEntity, chelseaEntity, "1", LocalDateTime.parse("2024-08-21T21:00:00"), MatchStatus.SCHEDULED);
        Match savedMatch = null;
        try {
            savedMatch = matchRepo.save(match);
            assertNotNull(savedMatch);
            assertNotNull(savedMatch.getId());

            List<Match> matches = matchRepo.findByLeagueRegionAndLeagueNameAndRoundAndHomeTeamName(
                    match.getLeague().getRegion(),
                    match.getLeague().getName(),
                    match.getRound(),
                    match.getHomeTeam().getName());

            assertEquals(1, matches.size());
        } finally {
            if (savedMatch != null) {
                matchRepo.delete(savedMatch);
            }
        }

    }
}
