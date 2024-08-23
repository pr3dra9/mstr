package rs.ac.bg.fon.mas.match_feed.dto; 

public class Score{
    public Halftime halftime;
    public Fulltime fulltime;
    public Extratime extratime;
    public Penalty penalty;

    @Override
    public String toString() {
        return "Score{" + "halftime=" + halftime + ", fulltime=" + fulltime + ", extratime=" + extratime + ", penalty=" + penalty + '}';
    }
    
}
