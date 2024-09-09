/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.messaging.config;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * @author Predrag
 */
@SpringBootTest
@TestPropertySource(properties = {"eureka.client.enabled=false", 
            "spring.cloud.config.enabled=false",
            "spring.cloud.stream.bindings.eventSupplier-out-0.destination=matchEvent",
            "spring.cloud.stream.bindings.eventSupplier-out-0.content-type=application/json",
            "spring.cloud.stream.kafka.binder.brokers=localhost",
            "spring.cloud.stream.kafka.binder.defaultBrokerPort=9093"})
@ActiveProfiles("test")
public class SendMessageTest {
    
    @Autowired
    StreamBridge streamBridge; 
    
    @Test
    @Disabled
    public void sendMessage() {
        streamBridge.send("eventSupplier-out-0", 
                "{\n" +
"	\"fixture\": {\n" +
"		\"fixtureId\": 12345,\n" +
"		\"fixtureStatusShort\": \"FT\",\n" +
"		\"fixtureElapsed\": 90\n" +
"	},\n" +
"	\"league\": {\n" +
"		\"name\": \"Premier League 2\",\n" +
"		\"country\": \"England\",\n" +
"		\"round\": \"Regular season - 4\"\n" +
"	},\n" +
"	\"teams\": {\n" +
"		\"home\": \"Southampton\",\n" +
"		\"away\": \"Manchester United\"\n" +
"	},\n" +
"	\"goals\": {\n" +
"		\"home\": 1,\n" +
"		\"away\": 1\n" +
"	}\n" +	
"}");
    }
    
}
