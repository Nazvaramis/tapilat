package isp.lab8.airways;
import lombok.*;
import java.util.List;


public class Route {

    private String name;
    private List<Waypoint> waypoints;

    public Route(String name, List<Waypoint> waypoints) {
        this.name = name;
        this.waypoints = waypoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Waypoint> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<Waypoint> waypoints) {
        this.waypoints = waypoints;
    }
}
