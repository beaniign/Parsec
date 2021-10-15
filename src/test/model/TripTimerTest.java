package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class TripTimerTest {

    TripTimer testTimer;

    @BeforeEach
    void runBefore() {
        this.testTimer = new TripTimer(12);
    }

    @Test
    void testGetMinutes(){
        assertEquals(11, testTimer.getMinutes());
    }

    @Test
    void testGetSeconds(){
        assertEquals(60, testTimer.getSeconds());
    }

    @Test
    void testConstructor(){
        assertEquals(60, testTimer.getSeconds());
        assertEquals(11, testTimer.getMinutes());
    }

    @Test
    void testUpdate(){
        try {
            testTimer.update();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(-1, testTimer.getMinutes());
        assertEquals(60, testTimer.getSeconds());
    }
}
