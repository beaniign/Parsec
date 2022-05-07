package persistence;

import model.Trip;
import model.TripLog;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// This class references code from JsonSerializationDemo
// url: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            TripLog tripLog = new TripLog();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyTripLog() {
        try {
            TripLog tripLog = new TripLog();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTripLog.json");
            writer.open();
            writer.write(tripLog);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTripLog.json");
            tripLog = reader.read();
            assertEquals(0, tripLog.length());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralTripLog() {
        try {
            TripLog tripLog = new TripLog();
            tripLog.addTrip(new Trip(20,"Moon", "CPSC 210"));
            tripLog.addTrip(new Trip(45,"Mars", "CPSC 210"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTripLog.json");
            writer.open();
            writer.write(tripLog);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTripLog.json");
            tripLog = reader.read();
            List<Trip> trips = tripLog.convertTripLogToList();
            assertEquals(2, trips.size());
            checkTrip(20, "Moon", "CPSC 210", trips.get(0));
            checkTrip(45, "Mars", "CPSC 210", trips.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}