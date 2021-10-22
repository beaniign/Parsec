package persistence;



import model.Trip;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkTrip(int duration, String location, String note, Trip trip) {
        assertEquals(duration, trip.getDuration());
        assertEquals(location, trip.getLocation());
        assertEquals(note, trip.getNote());
    }
}
