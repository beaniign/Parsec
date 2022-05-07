package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

// This class references code from AlarmSystem
// url: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem

// represents a log of trip log events
// the Singleton Design Pattern is used to ensure that there is only one EventLog in the system and that the system
// has global access to the single instance of the EventLog
public class EventLog implements Iterable<Event> {
    private static EventLog theLog;
    private Collection<Event> events;

    // MODIFIES: this
    // EFFECTS: Constructor / prevents external construction
    private EventLog() {
        events = new ArrayList<Event>();
    }

    // MODIFIES: this
    // EFFECTS: gets instance of EventLog OR create it if it doesn't already exist
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }

        return theLog;
    }

    // MODIFIES: this
    // EFFECTS: adds an event to the event log
    public void logEvent(Event e) {
        events.add(e);
    }

    // MODIFIES: this
    // EFFECTS: clears the event log and logs the event
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    // EFFECTS: iterates over the collection of events
    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}
