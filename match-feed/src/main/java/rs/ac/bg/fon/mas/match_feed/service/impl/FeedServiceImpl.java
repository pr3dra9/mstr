/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rs.ac.bg.fon.mas.match_feed.dto.Response;
import rs.ac.bg.fon.mas.match_feed.dto.Root;
import rs.ac.bg.fon.mas.match_feed.service.FeedService;

/**
 *
 * @author Predrag
 */
@Service
public class FeedServiceImpl implements FeedService {
    private static final Logger logger = LoggerFactory.getLogger(FeedServiceImpl.class);
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${api.key.name}")
    private String keyName;

    @Value("${api.key.value}")
    private String keyValue;
    
    @Value("${api.base-url}")
    private String baseUrl;

    @Value("${api.endpoints.get-matches}")
    private String matchEndpoint;

    @Value("${api.endpoints.get-finished-matches}")
    private String finishedMatchEndpoint;
    
    @Override
    public List<Response> fetchFeed() {
        String url = baseUrl + matchEndpoint;
        logger.debug("fetchFeed -> URL: " + url);

        HttpHeaders headers = new HttpHeaders();
        headers.set(keyName, keyValue);
        headers.set("Accept", "application/json");
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        ResponseEntity<Root> responseEntity = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            Root.class
        );

        Root root = responseEntity.getBody();
        logger.trace("fetchFeed -> root: " + root.toString());
        
        if (root != null)
            return root.response;
        return List.of();
    }

    @Override
    public List<Response> fetchFinished() {
        String url = baseUrl + finishedMatchEndpoint + getTodayDate();
        logger.debug("fetchFinished -> URL: " + url);

        HttpHeaders headers = new HttpHeaders();
        headers.set(keyName, keyValue);
        headers.set("Accept", "application/json");
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        ResponseEntity<Root> responseEntity = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            Root.class
        );

        Root root = responseEntity.getBody();
        
        if (root != null) {
            logger.trace("fetchFeed -> root: " + root.toString());
            return root.response;
        }
        
        logger.trace("fetchFeed -> root is empty");
        return List.of();
    }

    private String getTodayDate() {
        LocalDate today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter.ISO_LOCAL_DATE);
        return formattedDate;
    }
    
}
