package model;

import org.json.JSONObject;

// Represents a trip with duration, location, and a trip note
public class Trip {

    protected int duration;
    protected String location;
    protected String note;

    // MODIFIES: this
    // EFFECTS: constructs a trip with the corresponding values given
    public Trip(int duration, String location, String note) {
        this.duration = duration;
        this.note = note;
        this.location = location;
    }

    // EFFECTS: returns the value of a trip's duration as an integer
    public int getDuration() {
        return duration;
    }

    // EFFECTS: returns the value of a trip's location as a string
    public String getLocation() {
        return location;
    }

    // EFFECTS: returns the value of a trip's note as a string
    public String getNote() {
        return note;
    }
}
