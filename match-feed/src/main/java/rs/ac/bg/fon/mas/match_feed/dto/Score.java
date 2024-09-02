/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.match_feed.dto;

/**
 *
 * @author Predrag
 */
public class Score{
    public Halftime halftime;
    public Fulltime fulltime;
    public Extratime extratime;
    public Penalty penalty;

    public Score() {
    }

    public Score(Halftime halftime, Fulltime fulltime, Extratime extratime, Penalty penalty) {
        this.halftime = halftime;
        this.fulltime = fulltime;
        this.extratime = extratime;
        this.penalty = penalty;
    }
    
    @Override
    public String toString() {
        return "Score{" + "halftime=" + halftime + ", fulltime=" + fulltime + ", extratime=" + extratime + ", penalty=" + penalty + '}';
    }
    
}
