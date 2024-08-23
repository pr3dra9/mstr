package rs.ac.bg.fon.mas.match_feed.dto; 

public class Teams{
    public Home home;
    public Away away;

    @Override
    public String toString() {
        return "Teams{" + "home=" + home + ", away=" + away + '}';
    }
    
}
