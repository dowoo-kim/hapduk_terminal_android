package pe.dwkim.hapduk_terminal.Model;

import java.sql.Time;

/**
 * Created by dwkim on 16. 5. 24..
 */
public class StopInfo {
    private int id;
    private String name;
    private int routeId;
    private int stops_in_route_id;
    private int sequence;
    private int is_last_item;
    private int time_table_id;
    private String departure_time;
    private int child;
    private int teenager;
    private int adult;
    private String required;

    public StopInfo(int id, String name, int routeId, int stops_in_route_id, int sequence, int is_last_item, int time_table_id, String departure_time, int child, int teenager, int adult, String required) {
        this.id = id;
        this.name = name;
        this.routeId = routeId;
        this.stops_in_route_id = stops_in_route_id;
        this.sequence = sequence;
        this.is_last_item = is_last_item;
        this.time_table_id = time_table_id;
        this.departure_time = departure_time;
        this.child = child;
        this.teenager = teenager;
        this.adult = adult;
        this.required = required;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getStops_in_route_id() {
        return stops_in_route_id;
    }

    public void setStops_in_route_id(int stops_in_route_id) {
        this.stops_in_route_id = stops_in_route_id;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public int getIs_last_item() {
        return is_last_item;
    }

    public void setIs_last_item(int is_last_item) {
        this.is_last_item = is_last_item;
    }

    public int getTime_table_id() {
        return time_table_id;
    }

    public void setTime_table_id(int time_table_id) {
        this.time_table_id = time_table_id;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public int getChild() {
        return child;
    }

    public String getChildString(){
        return Integer.toString(this.child);
    }

    public void setChild(int child) {
        this.child = child;
    }

    public int getTeenager() {
        return teenager;
    }

    public String getTeenagerString(){
        return Integer.toString(this.teenager);
    }

    public void setTeenager(int teenager) {
        this.teenager = teenager;
    }

    public int getAdult() {
        return adult;
    }

    public String getAdultString(){
        return Integer.toString(this.adult);
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }
}
