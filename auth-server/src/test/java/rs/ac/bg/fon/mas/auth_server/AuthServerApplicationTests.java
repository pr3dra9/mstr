package rs.ac.bg.fon.mas.auth_server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"eureka.client.enabled=false", "spring.cloud.config.enabled=false"})
class AuthServerApplicationTests {

	@Test
	void contextLoads() {
	}

}
