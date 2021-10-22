package persistence;


import model.Trip;
import model.TripLog;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            TripLog tripLog = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyTripLog() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTripLog.json");
        try {
            TripLog tripLog = reader.read();
            assertEquals(0, tripLog.length());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }



    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTripLog.json");
        try {
            TripLog tripLog = reader.read();
            List<Trip> trips = tripLog.convertTripLogToList();
            assertEquals(1, trips.size());
            checkTrip(20, "Moon", "CPSC 210", trips.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}