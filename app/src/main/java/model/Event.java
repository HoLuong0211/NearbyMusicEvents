package model;

/**
 * Created by Administrator on 7/9/2016.
 */
public class Event {

    private int id;
    private String title;
    private String description;
    private String url;
    private String start_time;
    private String stop_time;
    private String venue_id;
    private String venue_name;
    private String venue_address;
    private double latitude;
    private double longitude;

    public Event(int id, String title, String description, String url, String start_time, String stop_time, String venue_id, String venue_name, String venue_address, double latitude, double longitude) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.start_time = start_time;
        this.stop_time = stop_time;
        this.venue_id = venue_id;
        this.venue_name = venue_name;
        this.venue_address = venue_address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Event() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getStop_time() {
        return stop_time;
    }

    public void setStop_time(String stop_time) {
        this.stop_time = stop_time;
    }

    public String getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(String venue_id) {
        this.venue_id = venue_id;
    }

    public String getVenue_name() {
        return venue_name;
    }

    public void setVenue_name(String venue_name) {
        this.venue_name = venue_name;
    }

    public String getVenue_address() {
        return venue_address;
    }

    public void setVenue_address(String venue_address) {
        this.venue_address = venue_address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
