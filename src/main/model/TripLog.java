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
                        "Trip " + tnum + " --- location: " + next.location + ", travel time: "
                                + next.duration + " minutes, journey note: " + "\"" + next.note + "\".");
            }
        }
    }

    public void clearLog() {
        trips.clear();
    }

    public void deleteLogElement(int i) {
        trips.remove(i);
    }

}

