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
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import rs.ac.bg.fon.mas.ticketing.dto.MatchDto;

/**
 *
 * @author Predrag
 */
@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(properties = {
    "spring.cloud.config.enabled=false",
    "eureka.client.enabled=false",
    "api.scheduler.url=http://localhost:81/matches/by-ids",
    "spring.cloud.loadbalancer.enabled=false"
})
@AutoConfigureStubRunner(
        ids = {"rs.ac.bg.fon.mas:scheduler:+:stubs:81"},
	stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class SchedulerApiClientTest {

    @Autowired
    SchedulerApiClient client;

    @Test
    public void testGetMatchesByIds() {
        List<MatchDto> list = client.getMatchesByIds(List.of(1L, 2L));
        Assertions.assertEquals(2, list.size());
    }

}
