package rs.ac.bg.fon.mas.match_feed.dto; 

public class League{
    public String name;
    public String country;
    public int season;
    public String round;

    @Override
    public String toString() {
        return "League{" + "name=" + name + ", country=" + country + ", season=" + season + ", round=" + round + '}';
    }
}
