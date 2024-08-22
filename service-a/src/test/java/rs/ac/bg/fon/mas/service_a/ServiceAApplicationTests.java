package rs.ac.bg.fon.mas.service_a;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"eureka.client.enabled=false", "spring.cloud.config.enabled=false"})
class ServiceAApplicationTests {

	@Test
	void contextLoads() {
	}

}
