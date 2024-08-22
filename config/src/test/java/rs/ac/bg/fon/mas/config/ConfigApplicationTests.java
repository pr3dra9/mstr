package rs.ac.bg.fon.mas.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"eureka.client.enabled=false"})
class ConfigApplicationTests {

	@Test
	void contextLoads() {
	}

}
