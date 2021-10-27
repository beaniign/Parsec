package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ColonyTest {

    Colony testColony;

    @BeforeEach
    void runBefore() {
        this.testColony = new Colony();
    }

    @Test
    void testGetPopulation(){
        assertEquals(0,testColony.getPopulation());
    }

    @Test
    void testGetLevel(){
        assertEquals(0,testColony.getLevel());
    }
    @Test
    void testConstructor(){
        assertEquals(0, testColony.getPopulation());
        assertEquals(0, testColony.getLevel());
    }

    @Test
    void testAddPopulation() {
        testColony.addPopulation(100);
        assertEquals(100, testColony.getPopulation());
        testColony.addPopulation(20);
        assertEquals(120,testColony.getPopulation());
    }

    @Test
    void testSetLevel() {
        testColony.addPopulation(20);
        testColony.setLevel();
        assertEquals(0,testColony.getLevel());
        testColony.addPopulation(80);
        testColony.setLevel();
        assertEquals(1,testColony.getLevel());
    }

    @Test
    void testRemovePopulation() {
        testColony.addPopulation(100);
        testColony.removePopulation(50);
        assertEquals(50,testColony.getPopulation());
    }

    @Test
    void testResetPopultion() {
        testColony.addPopulation(100);
        testColony.resetPopulation();
        assertEquals(0, testColony.getPopulation());
    }
}
