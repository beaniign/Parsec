package model;

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
    public void displayLogElements() {
        if (trips.isEmpty()) {
            System.out.println("You have not been on any journeys yet!");
        } else {
            for (Trip next : trips) {
                int tnum = (1 + trips.indexOf(next));
                System.out.println(
                        "Your trip " + tnum + " was to " + next.location + " with a travel time of "
                                + next.duration + " minutes. Your journey note was " + "\"" + next.note + "\".");
            }
        }
    }

    public void clearLog() {
        trips.clear();
    }

}

