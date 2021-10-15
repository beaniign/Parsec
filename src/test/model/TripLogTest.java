package model;

import model.exception.EmptyLogException;
import model.exception.TripDoesNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TripLogTest {

    private TripLog testTripLog;
    private Trip testTrip1;
    private Trip testTrip2;

    @BeforeEach
    void runBefore() {
        this.testTripLog = new TripLog();
        this.testTrip1 = new Trip(30, "Mars", "CPSC210");
        this.testTrip2 = new Trip(15, "Moon", "CPSC210");
    }

    @Test
    void testConvertTripLogToList(){
        testTripLog.addTrip(testTrip1);
        testTripLog.addTrip(testTrip2);
        List<Trip> testList = testTripLog.convertTripLogToList();
        assertEquals(2, testList.size());
        assertFalse(testList.isEmpty());
        testList.remove(0);
        testList.remove(0);
        assertTrue(testList.isEmpty());
    }
    @Test
    void testConstructor() {
        assertEquals(0, testTripLog.length());
    }

    @Test
    void testIsEmptyLogTRUE(){
        assertTrue(testTripLog.isEmptyLog());
    }

    @Test
    void testIsEmptyLogFALSE(){
        testTripLog.addTrip(testTrip1);
        testTripLog.addTrip(testTrip2);
        assertFalse(testTripLog.isEmptyLog());
    }

    @Test
    void testAddTrip() {
        testTripLog.addTrip(testTrip1);
        assertEquals(1, testTripLog.length());
        testTripLog.addTrip(testTrip2);
        assertEquals(2, testTripLog.length());
    }

    @Test
    void testClearLog() {
        testTripLog.addTrip(testTrip1);
        testTripLog.addTrip(testTrip2);
        testTripLog.clearTripLog();
        assertEquals(0, testTripLog.length());
    }

    @Test
    void testDeleteLogElementTYPICAL() {
        testTripLog.addTrip(testTrip1);
        testTripLog.addTrip(testTrip2);
        try {
            testTripLog.deleteLogElement(1);
            assertEquals(1, testTripLog.length());
        } catch (EmptyLogException | TripDoesNotExistException e) {
            e.printStackTrace();
        }

    }
    @Test
    void testDeleteLogElementDNE1() {
        testTripLog.addTrip(testTrip1);

        try {
            testTripLog.deleteLogElement(1);
            assertEquals(0, testTripLog.length());
            testTripLog.deleteLogElement(1);
        } catch (EmptyLogException | TripDoesNotExistException e) {
            e.printStackTrace();
        }

    }
    @Test
    void testDeleteLogElementDNE2() {
        testTripLog.addTrip(testTrip1);
        try {
            testTripLog.deleteLogElement(3);
        } catch (EmptyLogException | TripDoesNotExistException e) {
            e.printStackTrace();
        }

    }


    @Test
    void testDeleteLogElementsEmpty() {
        assertEquals(0, testTripLog.length());
        try {
            testTripLog.deleteLogElement(1);
            assertEquals(0, testTripLog.length());
        } catch (EmptyLogException | TripDoesNotExistException e) {
            e.printStackTrace();
        }
    }
}
