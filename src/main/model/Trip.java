package model;

public class Trip {

    protected int duration;
    protected String location;
    protected String note;

    //constructor
    public Trip(int duration, String location, String note) {
        this.duration = duration;
        this.note = note;
        this.location = location;
    }

    public int getDuration() {
        return duration;
    }

    public String getLocation() {
        return location;
    }

    public String getNote() {
        return note;
    }
}
