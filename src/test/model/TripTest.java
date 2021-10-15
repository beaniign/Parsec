package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TripTest {

    private Trip testTrip;

    @BeforeEach
    void runBefore() {
        this.testTrip= new Trip(15, "Mars", "CPSC210");
    }

    @Test
    void testConstructor(){
        assertEquals(15, testTrip.getDuration());
        assertEquals("Mars", testTrip.getLocation());
        assertEquals("CPSC210", testTrip.getNote());
    }

    @Test
    void testSetTimer(){

    }
}
