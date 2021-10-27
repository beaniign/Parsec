package model;

// Represents a colonies that a user can choose to go to
public class Colony {

    protected int level;
    protected int population;

    // MODIFIES: this
    // EFFECTS: constructs a colony with a population and level of 0
    public Colony() {
        this.level = 0;
        this.population = 0;
    }

    // EFFECTS: returns the value of a colony's population as an integer
    public int getPopulation() {
        return population;
    }

    // EFFECTS: returns the value of a colony's level as an integer
    public int getLevel() {
        return level;
    }

    // MODIFIES: this
    // EFFECTS: updates the level of a colony by the fact that ever level takes an additional 100 people
    public void setLevel() {
        this.level = Math.floorDiv(population, 100);
    }

    // MODIFIES: this
    // EFFECTS: adds the amount of colonizers given to the current population and sets that as the new population
    public void addPopulation(int colonizers) {
        this.population += colonizers;
    }

    // MODIFIES: this
    // EFFECTS: subtracts the amount given from the current population and sets that as the new population
    //          mainly used for when a trip is deleted
    public void removePopulation(int colonizers) {
        this.population -= colonizers;
    }

    // MODIFIES: this
    // EFFECTS: resets the population of a colony, used for when an entire trip log is cleared
    public void resetPopulation() {
        this.population = 0;
    }


}




