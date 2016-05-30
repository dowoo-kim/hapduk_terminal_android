package pe.dwkim.hapduk_terminal.Model;

import java.sql.Time;
import java.util.List;

/**
 * Created by dwkim on 16. 5. 24..
 */
public class Route {
    private StopInfo destination;
    private List<StopInfo> stopsInRoute;

    public Route(StopInfo destination, List<StopInfo> stopsInRoute) {
        this.destination = destination;
        this.stopsInRoute = stopsInRoute;
    }

    public StopInfo getDestination() {
        return destination;
    }

    public void setDestination(StopInfo destination) {
        this.destination = destination;
    }

    public List<StopInfo> getStopsInRoute() {
        return stopsInRoute;
    }

    public void setStopsInRoute(List<StopInfo> stopsInRoute) {
        this.stopsInRoute = stopsInRoute;
    }

    @Override
    public String toString() {
        return "Route{" +
                ", destination=" + destination +
                ", stopsInRoute=" + stopsInRoute +
                '}';
    }
}
