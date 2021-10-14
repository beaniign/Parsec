package ui;


import model.*;

import java.util.Scanner;


public class ParsecApp {

    private Colony mars;
    private Colony moon;
    private Colony jupiter;
    private Colony saturn;
    private TripLog log;

    // EFFECTS: runs Parsec
    public ParsecApp() {
        startParsec();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void startParsec() {
        Scanner input = new Scanner(System.in);
        // Learn Java the Hard Way textbook
        initializeColonies();
        initializeTripLog();

        boolean mainContinue = true;
        String mainOption = null;

        while (mainContinue) {
            displayMenu();
            mainOption = input.next();

            if (mainOption.equals("EXT")) {
                mainContinue = false;
            } else {
                handleOptions(mainOption);
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: processes the option selected by the user
    protected void handleOptions(String option) {
        if (option.equals("NEW")) {
            makeNewTrip();
        } else if (option.equals("LOG")) {
            getLog();
        } else if (option.equals("LVL")) {
            getLvl();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    public void makeNewTrip() {
        Scanner input = new Scanner(System.in);
        System.out.println("Which location would you like to send your astronauts to?");
        String location = input.next();
        System.out.println("How long would you like to set the timer for?");
        int time = input.nextInt();
        System.out.println("Any notes you would like to add to the journey log?");
        String note = input.next();
        Trip myTrip = new Trip(time, location, note);
        System.out.println("You have selected " + location + "! " + "The journey will take " + time + " minutes!");
        setLocation(location, time);
        myTrip.setTimer(time);
        System.out.println("Time's up!");
        greet(location);
        log.addTrip(myTrip);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nHello there Captain! What can I do for you?");
        System.out.println("\t-------------------------------");
        System.out.println("\tNEW -> New Trip");
        System.out.println("\tLOG -> Check the Trip Logs");
        System.out.println("\tLVL -> Check your Colony Levels");
        System.out.println("\tEXT -> Exit the program");
        System.out.println("\t-------------------------------");
    }


    public void initializeTripLog() {
        this.log = new TripLog();
    }

    public void initializeColonies() {
        this.mars = new Colony();
        this.moon = new Colony();
        this.jupiter = new Colony();
        this.saturn = new Colony();
    }

    public void greet(String s) {
        if (s.equals("Mars")) {
//            System.out.println("Howdy there welcome to our humble red planet!");
            System.out.println("The level of your Mars colony is " + mars.getLevel());
            System.out.println("The population of your Mars colony is " + mars.getPopulation());
        }
        if (s.equals("Moon")) {
//            System.out.println("As your close neighbours we welcome you!");
            System.out.println("The level of your Moon colony is " + moon.getLevel());
            System.out.println("The population of your Moon colony is " + moon.getPopulation());
        }
        if (s.equals("Jupiter")) {
//            System.out.println("Make sure to stay hydrated! Haha, get it? Because we're surrounded by water ?");
            System.out.println("The level of your Jupiter colony is " + jupiter.getLevel());
            System.out.println("The population of your Jupiter colony is " + jupiter.getPopulation());
        }
        if (s.equals("Saturn")) {
//            System.out.println("Welcome, hopefully your long journey was not too stressful!");
            System.out.println("The level of your Saturn colony is " + saturn.getLevel());
            System.out.println("The population of your Saturn colony is " + saturn.getPopulation());
        }

    }

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

    public void getLog() {
        Scanner input = new Scanner(System.in);
        boolean logContinue = true;
        String logOption = null;

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

    protected void handleLogOptions(String logOption) {
        if (logOption.equals("CHK")) {
            log.displayLogElements();
        } else if (logOption.equals("CLR")) {
            log.clearLog();
            System.out.println("Your log has been cleared!");
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void displayLogMenu() {
        System.out.println("\nSelect a Log related option from below!");
        System.out.println("\t- - - - - - - - - - - - - - - -");
        System.out.println("\tCHK -> Check logs");
        System.out.println("\tCLR -> Clear logs");
        System.out.println("\tEXT -> Back to the main menu");
        System.out.println("\t- - - - - - - - - - - - - - - -");
    }

    public void getLvl() {
        System.out.println("Your Moon colony level is " + moon.getLevel()
                + " with a total population of " + moon.getPopulation() + "!");
        System.out.println("Your Mars colony level is " + mars.getLevel()
                + " with a total population of " + mars.getPopulation() + "!");
        System.out.println("Your Jupiter colony level is " + jupiter.getLevel()
                + " with a total population of " + jupiter.getPopulation() + "!");
        System.out.println("Your Saturn colony level is " + saturn.getLevel()
                + " with a total population of " + saturn.getPopulation() + "!");

    }
}

