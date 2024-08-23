package rs.ac.bg.fon.mas.match_feed;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import rs.ac.bg.fon.mas.match_feed.dto.Root;

@SpringBootTest
@ActiveProfiles("test")
class MatchFeedApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testMapping() throws JsonProcessingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String json = """
                   {
                       "get": "fixtures",
                       "parameters": {
                           "live": "all"
                       },
                       "errors": [],
                       "results": 22,
                       "paging": {
                           "current": 1,
                           "total": 1
                       },
                       "response": [
                           {
                               "fixture": {
                                   "id": 1154793,
                                   "referee": null,
                                   "timezone": "UTC",
                                   "date": "2024-08-22T16:00:00+00:00",
                                   "timestamp": 1724342400,
                                   "periods": {
                                       "first": 1724342400,
                                       "second": null
                                   },
                                   "venue": {
                                       "id": 5121,
                                       "name": "Madla Handelslag Stadion",
                                       "city": "Hafrsfjord"
                                   },
                                   "status": {
                                       "long": "First Half",
                                       "short": "1H",
                                       "elapsed": 30
                                   }
                               },
                               "league": {
                                   "id": 775,
                                   "name": "3. Division - Girone 2",
                                   "country": "Norway",
                                   "logo": "https://media.api-sports.io/football/leagues/775.png",
                                   "flag": "https://media.api-sports.io/flags/no.svg",
                                   "season": 2024,
                                   "round": "Group 2 - 18"
                               },
                               "teams": {
                                   "home": {
                                       "id": 7009,
                                       "name": "Madla",
                                       "logo": "https://media.api-sports.io/football/teams/7009.png",
                                       "winner": null
                                   },
                                   "away": {
                                       "id": 7027,
                                       "name": "Sola",
                                       "logo": "https://media.api-sports.io/football/teams/7027.png",
                                       "winner": null
                                   }
                               },
                               "goals": {
                                   "home": 0,
                                   "away": 0
                               },
                               "score": {
                                   "halftime": {
                                       "home": 0,
                                       "away": 0
                                   },
                                   "fulltime": {
                                       "home": null,
                                       "away": null
                                   },
                                   "extratime": {
                                       "home": null,
                                       "away": null
                                   },
                                   "penalty": {
                                       "home": null,
                                       "away": null
                                   }
                               },
                               "events": []
                           },
                           {
                               "fixture": {
                                   "id": 1222180,
                                   "referee": null,
                                   "timezone": "UTC",
                                   "date": "2024-08-22T16:00:00+00:00",
                                   "timestamp": 1724342400,
                                   "periods": {
                                       "first": 1724342400,
                                       "second": null
                                   },
                                   "venue": {
                                       "id": 2610,
                                       "name": "National Stadium Ramat Gan",
                                       "city": "Ramat Gan"
                                   },
                                   "status": {
                                       "long": "First Half",
                                       "short": "1H",
                                       "elapsed": 29
                                   }
                               },
                               "league": {
                                   "id": 382,
                                   "name": "Liga Leumit",
                                   "country": "Israel",
                                   "logo": "https://media.api-sports.io/football/leagues/382.png",
                                   "flag": "https://media.api-sports.io/flags/il.svg",
                                   "season": 2024,
                                   "round": "Regular Season - 1"
                               },
                               "teams": {
                                   "home": {
                                       "id": 4489,
                                       "name": "Hapoel Ramat Gan",
                                       "logo": "https://media.api-sports.io/football/teams/4489.png",
                                       "winner": true
                                   },
                                   "away": {
                                       "id": 4497,
                                       "name": "Hapoel Kfar Saba",
                                       "logo": "https://media.api-sports.io/football/teams/4497.png",
                                       "winner": false
                                   }
                               },
                               "goals": {
                                   "home": 2,
                                   "away": 0
                               },
                               "score": {
                                   "halftime": {
                                       "home": 2,
                                       "away": 0
                                   },
                                   "fulltime": {
                                       "home": null,
                                       "away": null
                                   },
                                   "extratime": {
                                       "home": null,
                                       "away": null
                                   },
                                   "penalty": {
                                       "home": null,
                                       "away": null
                                   }
                               },
                               "events": [
                                   {
                                       "time": {
                                           "elapsed": 2,
                                           "extra": null
                                       },
                                       "team": {
                                           "id": 4489,
                                           "name": "Hapoel Ramat Gan",
                                           "logo": "https://media.api-sports.io/football/teams/4489.png"
                                       },
                                       "player": {
                                           "id": null,
                                           "name": null
                                       },
                                       "assist": {
                                           "id": null,
                                           "name": null
                                       },
                                       "type": "Goal",
                                       "detail": "Normal Goal",
                                       "comments": null
                                   },
                                   {
                                       "time": {
                                           "elapsed": 19,
                                           "extra": null
                                       },
                                       "team": {
                                           "id": 4489,
                                           "name": "Hapoel Ramat Gan",
                                           "logo": "https://media.api-sports.io/football/teams/4489.png"
                                       },
                                       "player": {
                                           "id": null,
                                           "name": null
                                       },
                                       "assist": {
                                           "id": null,
                                           "name": null
                                       },
                                       "type": "Goal",
                                       "detail": "Normal Goal",
                                       "comments": null
                                   }
                               ]
                           }
                       ]
                   }
                   """;
        
            Root root = objectMapper.readValue(json, Root.class);

            assertEquals(2, root.response.size());

    }

}
