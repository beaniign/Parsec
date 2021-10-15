package model;

import model.exception.EmptyLogException;
import model.exception.TripDoesNotExistException;

import java.util.ArrayList;
import java.util.List;

public class TripLog {
    private List<Trip> trips;

    public TripLog() {
        trips = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS:  adds a trip to the current trip log
    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    // EFFECTS: returns strings with the information of each trip in the log
    public List<Trip> convertTripLogToList() {
        return trips;
    }

    public boolean isEmptyLog() {
        return trips.isEmpty();
    }

    public int length() {
        return trips.size();
    }

    public void clearLog() {
        trips.clear();
    }

    public void deleteLogElement(int i) throws EmptyLogException, TripDoesNotExistException {
        if (trips.isEmpty()) {
            throw new EmptyLogException();
        }
        if ((i + 1) > trips.size()) {
            throw new TripDoesNotExistException();
        }
        if (i < 0) {
            throw new TripDoesNotExistException();
        } else {
            trips.remove(i);
        }
    }

}

