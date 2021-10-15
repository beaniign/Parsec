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

    protected int getDuration() {
        return duration;
    }

    protected String getLocation() {
        return location;
    }

    protected String getNote() {
        return note;
    }
}
