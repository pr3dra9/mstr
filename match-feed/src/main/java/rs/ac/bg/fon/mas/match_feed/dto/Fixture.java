package rs.ac.bg.fon.mas.match_feed.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class Fixture {

    public Long id;
    public String timezone;
    public String date;
    
    public String venueName;
    public FixtureStatus status;

    @JsonProperty("venue")
    private void unpackNameFromNestedObject(Map<String, String> venue) {
        venueName = venue.get("name");
    }

    @Override
    public String toString() {
        return "Fixture{" + "id=" + id + ", timezone=" + timezone + ", date=" + date + ", venueName=" + venueName + ", status=" + status + '}';
    }
    
}
