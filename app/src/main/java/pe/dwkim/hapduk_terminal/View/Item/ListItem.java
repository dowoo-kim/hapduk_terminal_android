package pe.dwkim.hapduk_terminal.View.Item;

import java.sql.Time;
import pe.dwkim.hapduk_terminal.Model.Enum.*;

/**
 * Created by dwkim on 16. 5. 25..
 */
public class ListItem {
    private int stopInfoid;
    private int routeId;
    private String departureTime;
    private String stopInfoName;
    private RecyclerViewType viewType;

    public ListItem(int stopInfoid, int routeId, String departureTime, String stopInfoName, int sequence) {
        this.stopInfoid = stopInfoid;
        this.routeId = routeId;
        this.departureTime = departureTime;
        this.stopInfoName = stopInfoName;
        if(sequence == 000){
            viewType = RecyclerViewType.FIRST_ITEM;
        }else{
            viewType = RecyclerViewType.ITEM;
        }
    }

    public int getStopInfoid() {
        return stopInfoid;
    }

    public void setStopInfoid(int stopInfoid) {
        this.stopInfoid = stopInfoid;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getStopInfoName() {
        return stopInfoName;
    }

    public void setStopInfoName(String stopInfoName) {
        this.stopInfoName = stopInfoName;
    }

    public RecyclerViewType getViewType() {
        return viewType;
    }

    public void setViewType(RecyclerViewType viewType) {
        this.viewType = viewType;
    }

    @Override
    public String toString() {
        return "ListItem{" +
                "stopInfoid=" + stopInfoid +
                ", routeId=" + routeId +
                ", departureTime=" + departureTime +
                ", stopInfoName=" + stopInfoName +
                ", viewType=" + viewType +
                '}';
    }
}
