/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;
import rs.ac.bg.fon.mas.match_feed.dto.Response;
import rs.ac.bg.fon.mas.match_feed.dto.Root;

/**
 *
 * @author Predrag
 */
@SpringBootTest
@ActiveProfiles("test")
public class FeedServiceTest {
 
    @MockBean
    RestTemplate restTemplate;
    
    @Autowired
    FeedService service;
    
    @Test
    public void testFetchFeed() {
        Root root = new Root(); // Set up the root object as needed
        root.response = new ArrayList<>(Arrays.asList(new Response(), new Response()));
        ResponseEntity<Root> responseEntity = new ResponseEntity<>(root, HttpStatus.OK);

        when(restTemplate.exchange(
            anyString(),
            eq(HttpMethod.GET),
            any(HttpEntity.class),
            eq(Root.class)
        )).thenReturn(responseEntity);
        
        List<Response> list = service.fetchFeed();

        Assertions.assertEquals(2, list.size());        
    }
    
}
