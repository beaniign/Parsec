package model;

public class Colony {

    protected int level;
    protected int population;

    public Colony() {
        this.level = 0;
        this.population = 0;
    }

    public void setLevel() {
        this.level = Math.floorDiv(population, 100);
    }

    public int getLevel() {
        return level;
    }

    public void addPopulation(int colonizers) {
        this.population += colonizers;
    }

    public int getPopulation() {
        return population;
    }

}




