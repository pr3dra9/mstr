package rs.ac.bg.fon.mas.scheduler;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"eureka.client.enabled=false", "spring.cloud.config.enabled=false"})
class SchedulerApplicationTests {

	@Test
	void contextLoads() {
	}

}
