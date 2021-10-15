package ui;

import model.*;
import model.exception.EmptyLogException;
import model.exception.TripDoesNotExistException;

import java.util.List;
import java.util.Scanner;

// Parsec focus / study tool application
public class ParsecApp {

    private Colony mars;
    private Colony moon;
    private Colony jupiter;
    private Colony saturn;
    private TripLog log;
    private Scanner input;

    // EFFECTS: runs Parsec
    public ParsecApp() {
        startParsec();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void startParsec() {
        initializeColonies();
        initializeTripLog();
        input = new Scanner(System.in);

        boolean mainContinue = true;
        String mainOption;

        while (mainContinue) {
            displayMenu();
            mainOption = input.next();

            if (mainOption.equals("EXT")) {
                System.out.println("See you soon!");
                mainContinue = false;
            } else {
                handleOptions(mainOption);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes the option selected by the user
    protected void handleOptions(String option) {
        switch (option) {
            case "NEW":
                makeNewTrip();
                break;
            case "LOG":
                startLog();
                break;
            case "LVL":
                displayLevel();
                break;
            default:
                System.out.println("Invalid Selection!");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new trip along with its timer
    public void makeNewTrip() {
        locationMenu();
        String location = input.next();

        if (checkpoint1(location)) {
            System.out.println("How long would you like to set the timer for?");
            int time = input.nextInt();

            if (checkpoint2(time, location)) {

                System.out.println("Any notes you would like to add to the journey log?");
                String note = input.next();
                Trip myTrip = new Trip(time, location, note);
                setLocation(location, time);
                setTimer(time);
                System.out.println("Time's up...we have arrived!");
                greet(location);
                log.addTrip(myTrip);

            } else {
                makeNewTrip();
            }

        } else {

            System.out.println("Invalid Selection!");
            makeNewTrip();

        }
    }

    // REQUIRES: a non-negative number for the amount of time
    // EFFECTS: starts a timer
    public void setTimer(int t) {
        TripTimer timer = new TripTimer(t);
        try {
            timer.update();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    // MODIFIES: this
    // EFFECTS: initializes the trip log
    public void initializeTripLog() {
        this.log = new TripLog();
    }

    // MODIFIES: this
    // EFFECTS: initializes the colonies
    public void initializeColonies() {
        this.mars = new Colony();
        this.moon = new Colony();
        this.jupiter = new Colony();
        this.saturn = new Colony();
    }

    // EFFECTS: prints out string for each colony after arriving
    public void greet(String s) {
        System.out.println("The level of your " + s + " colony is " + whichColony(s).getLevel());
        System.out.println("The population of your " + s + " colony is " + whichColony(s).getPopulation());
    }

    // EFFECTS: returns each colony according to the user's choice
    public Colony whichColony(String s) {
        switch (s) {
            case "Mars":
                return mars;
            case "Moon":
                return moon;
            case "Jupiter":
                return jupiter;
            case "Saturn":
                return saturn;
        }
        return null;
    }

    // EFFECTS: returns true if the location selected is one of the four valid choices
    private boolean checkpoint1(String location) {
        return location.equals("Moon") || location.equals("Mars")
                || location.equals("Jupiter") || location.equals("Saturn");
    }

    // EFFECTS: returns true if the location selected along with the time selected meets the requirements of each colony
    private boolean checkpoint2(int duration, String location) {
        if (location.equals("Mars") && duration < 30) {
            System.out.println("You cannot reach Mars in " + duration + " minutes!");
            return false;
        } else if (location.equals("Jupiter") && duration < 45) {
            System.out.println("You cannot reach Jupiter in " + duration + " minutes!");
            return false;
        } else if (location.equals("Saturn") && duration < 60) {
            System.out.println("You cannot reach Saturn in " + duration + " minutes!");
            return false;
        } else {
            return true;
        }
    }

    // MODIFIES: this
    // EFFECTS: adds the correct value of people to the population, then updates the colony's level
    public void setLocation(String s, int i) {
        if (s.equals("Mars")) {
            mars.addPopulation(i);
            mars.setLevel();
        }
        if (s.equals("Moon")) {
            moon.addPopulation(i);
            moon.setLevel();
        }
        if (s.equals("Jupiter")) {
            jupiter.addPopulation(i);
            jupiter.setLevel();
        }
        if (s.equals("Saturn")) {
            saturn.addPopulation(i);
            saturn.setLevel();
        }
    }

    // MODIFIES: this
    // EFFECTS: starts the log menu
    public void startLog() {
        boolean logContinue = true;
        String logOption;

        while (logContinue) {
            displayLogMenu();
            logOption = input.next();

            if (logOption.equals("EXT")) {
                logContinue = false;
            } else {
                handleLogOptions(logOption);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command during the log menu
    protected void handleLogOptions(String logOption) {
        switch (logOption) {
            case "CHK":
                checkTripLog();
                break;
            case "CLR":
                log.clearTripLog();
                System.out.println("Your log has been cleared!");
                break;
            case "DEL":
                deleteTripLog();
                break;
            default:
                System.out.println("Invalid selection!");
        }
    }

    // EFFECTS: displays the trips' information that's currently in the trip log, or error message if the log is empty
    public void checkTripLog() {
        if (log.isEmptyLog()) {
            System.out.println("Your logs are empty!");
        } else {
            List<Trip> trips;
            trips = log.convertTripLogToList();
            for (Trip next : trips) {
                int tnum = (1 + trips.indexOf(next));
                System.out.println(
                        "Trip " + tnum + " --- location: " + next.getLocation() + ", travel time: "
                                + next.getDuration() + " minutes, journey note: "
                                + "\"" + next.getNote() + "\".");

            }
        }
    }

    // MODIFIES: this
    // EFFECTS: deletes a selected trip from the trip log (by index), or print out error messages when the index
    //          given is negative or if the trip log is currently empty
    public void deleteTripLog() {
        System.out.println("Enter the index of the trip you would like to delete:");
        int index = (input.nextInt() - 1);
        if (index < 0) {
            System.out.println("The index cannot be negative or zero!");
        } else {
            try {
                log.deleteLogElement(index);
                System.out.println("Your log entry has been deleted!");
            } catch (EmptyLogException e) {
                System.out.println("Your logs are empty!");
            } catch (TripDoesNotExistException e) {
                System.out.println("That log entry does not exist!");
            }
        }
    }

    // EFFECTS: displays the log menu with options to view and / or edit the trip log
    private void displayLogMenu() {
        System.out.println("\nPlease select an option from below:");
        System.out.println("\t-  -  -  -  -  -  -  -  -  -  -  -");
        System.out.println("\tCHK -> Check log entries");
        System.out.println("\tCLR -> Clear log entries");
        System.out.println("\tDEL -> Delete a specific log entry");
        System.out.println("\tEXT -> Back to the main menu");
        System.out.println("\t-  -  -  -  -  -  -  -  -  -  -  -");
    }

    // EFFECTS: displays the level of the colonies
    public void displayLevel() {
        System.out.println("Your Moon colony level is " + moon.getLevel()
                + " with a total population of " + moon.getPopulation() + "!");
        System.out.println("Your Mars colony level is " + mars.getLevel()
                + " with a total population of " + mars.getPopulation() + "!");
        System.out.println("Your Jupiter colony level is " + jupiter.getLevel()
                + " with a total population of " + jupiter.getPopulation() + "!");
        System.out.println("Your Saturn colony level is " + saturn.getLevel()
                + " with a total population of " + saturn.getPopulation() + "!");

    }

    // EFFECTS: displays the available locations to the user
    private void locationMenu() {
        System.out.println("\nWhere would you like to go?");
        System.out.println("\t+   -   -   -   -   -   -   -   +");
        System.out.println("\tMoon -------- requires 15 minutes");
        System.out.println("\tMars -------- requires 30 minutes");
        System.out.println("\tJupiter ----- requires 45 minutes");
        System.out.println("\tSaturn ------ requires 60 minutes");
        System.out.println("\t+   -   -   -   -   -   -   -   +");
    }

    // EFFECTS: displays main menu of options to user
    private void displayMenu() {
        System.out.println("\nHello there Captain! What can I do for you?");
        System.out.println("\t-------------------------------");
        System.out.println("\tNEW -> New trip");
        System.out.println("\tLOG -> Check and edit trip logs");
        System.out.println("\tLVL -> Check your colony Levels");
        System.out.println("\tEXT -> Exit the program");
        System.out.println("\t-------------------------------");
    }

}

