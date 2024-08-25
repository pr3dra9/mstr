/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.ticketing.client;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import rs.ac.bg.fon.mas.ticketing.dto.MatchDto;

/**
 *
 * @author Predrag
 */
@SpringBootTest(properties = {"eureka.client.enabled=true", "spring.cloud.config.enabled=true"})
@ActiveProfiles("test")
public class SchedulerApiClientTest {

    //@MockBean
    //RestTemplate restTemplate;

    @Autowired
    SchedulerApiClient client;

    @Test
    @Disabled
    public void testGetMatchesByIds() {
        List<MatchDto> list = client.getMatchesByIds(List.of(1L, 2L));
        Assertions.assertEquals(2, list.size());
    }

}
