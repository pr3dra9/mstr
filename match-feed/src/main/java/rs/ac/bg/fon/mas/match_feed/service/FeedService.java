/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.service;

import java.util.List;
import rs.ac.bg.fon.mas.match_feed.dto.Response;

/**
 *
 * @author Predrag
 */
public interface FeedService {
    List<Response> fetchFeed();
}
