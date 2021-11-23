package model;

import model.exception.TripDoesNotExistException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// This class references code from JsonSerializationDemo
// url: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// Represents a list of trips
public class TripLog implements Writable {
    private List<Trip> trips;

    // EFFECTS: instantiates a new trip log with no trips inside
    public TripLog() {
        trips = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS:  adds a trip to the current trip log
    public void addTrip(Trip trip) {
        trips.add(trip);
        String location = trip.location.substring(0, 1).toUpperCase() + trip.location.substring(1);
        EventLog.getInstance().logEvent(new Event("New trip to " + location + " added."));
    }

    // EFFECTS: returns the trips inside trip log as a list of trips instead of a TripLog
    public List<Trip> convertTripLogToList() {
        return trips;
    }

    // EFFECTS: returns true if the trip log is empty
    public boolean isEmptyLog() {
        return trips.isEmpty();
    }

    // EFFECTS: returns the number of trips inside a trip log
    public int length() {
        return trips.size();
    }

    // EFFECTS: returns the ith trip
    public Trip getTrip(int i) {
        return trips.get(i);
    }

    // MODIFIES: this
    // EFFECTS: removes all elements within a trip log
    public void clearTripLog() {
        trips.clear();
        EventLog.getInstance().logEvent(new Event("Trip log cleared."));
    }

    public int logSize() {
        return trips.size();
    }

    // MODIFIES: this
    // EFFECTS: removes an element inside a trip log based on the integer given, that will be the index of the trip
    //          that will be removed, also throws exceptions when the trip log is empty, or when the index given is
    //          invalid
    public void deleteLogElement(int i) throws TripDoesNotExistException {
        if ((i + 1) > trips.size()) {
            throw new TripDoesNotExistException();
        } else {
            trips.remove(i);
            EventLog.getInstance().logEvent(new Event("Trip at position " + (i + 1) + " has been deleted."));
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("trips", tripsToJson());
        EventLog.getInstance().logEvent(new Event("Trips saved."));
        return json;
    }

    // EFFECTS: returns trips in this triplog as a JSON array
    private JSONArray tripsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Trip t : trips) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

}

