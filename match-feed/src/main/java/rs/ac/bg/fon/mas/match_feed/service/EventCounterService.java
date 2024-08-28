/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.service;

import rs.ac.bg.fon.mas.match_feed.domain.EventCounter;

/**
 *
 * @author Predrag
 */
public interface EventCounterService {
    public int getCounter(Long uuid);
    public EventCounter saveEventCounter(EventCounter counter);
    public boolean isFinished(Long uuid);
    public boolean setFinished(Long uuid);
}
