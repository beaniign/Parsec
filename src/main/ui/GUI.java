package ui;

import model.Colony;
import model.Trip;
import model.TripLog;
import model.exception.TripDoesNotExistException;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// Below are a collection of resources that assisted me in creating the Parsec App:
// https://www.tutorialspoint.com/how-to-set-the-location-of-a-button-anywhere-in-jframe
// https://stackoverflow.com/questions/6593322/why-does-the-jframe-setsize-method-not-set-the-size-correctly
// https://stackoverflow.com/questions/20462167/increasing-font-size-in-a-jbutton
// https://stackoverflow.com/questions/14627223/how-to-change-a-jbutton-color-on-mouse-pressed
// https://www.codejava.net/java-se/swing/jlabel-basic-tutorial-and-examples
// https://stackoverflow.com/questions/12998604/adding-fonts-to-swing-application-and-include-in-package
// https://www.behance.net/gallery/128359661/Nagoda-Free-Modern-Display-Font?tracking_source=search_projects_recommended%7Cfree%20fonts
// https://stackoverflow.com/questions/4585867/transparent-jbutton/4586003
// https://stackoverflow.com/questions/2281937/swing-jtextfield-how-to-remove-the-border
// https://docs.oracle.com/javase/7/docs/api/java/util/Timer.html
// https://docs.oracle.com/javase/tutorial/uiswing/misc/timer.html
// https://stackoverflow.com/questions/10820033/make-a-simple-timer-in-java/14323134


// represents the GUI for Parsec
public class GUI {
    private static final String SAVED_LOGS = "./data/logbook.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private boolean isSaved;

    private JFrame frame;
    private JPanel mainPanel;

    private Colony mars;
    private Colony moon;
    private Colony jupiter;
    private Colony saturn;
    private TripLog log;

    // MODIFIES: this
    // EFFECTS: construct the frame with buttons leading to all panels, also creates instances of the
    //          four colonies as well as an empty trip log
    public GUI() {
        mainPanel = new MainPanel(this);
        initializeJson();
        initializeTripLog();
        initializeColonies();
        initializeTripLog();
        initializeFrame();
    }

    // MODIFIES: this
    // EFFECTS: creates a JsonWriter and JsonReader, sets the isSaved field to true as there are no changes made yet
    public void initializeJson() {
        isSaved = true;
        jsonWriter = new JsonWriter(SAVED_LOGS);
        jsonReader = new JsonReader(SAVED_LOGS);
    }

    // MODIFIES: this
    // EFFECTS: creates the main frame of the parsec gui
    public void initializeFrame() {
        frame = new JFrame("Parsec");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/ui/images/Parsec Favicon.png"));
        frame.getContentPane().add(mainPanel);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
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

    // MODIFIES: this
    // EFFECTS: creates a new trip object with the given parameters, then add it to the trip log while tell the
    //          application that there are changes that have not been saved yet
    public void createNewTrip(int duration, String location, String note) {
        Trip newTrip = new Trip(duration, location, note);
        setLocation(location, duration);
        log.addTrip(newTrip);
        isSaved = false;
    }

    // MODIFIES: this
    // EFFECTS: updates the population and level of a colony based on the given parameters
    public void setLocation(String s, int i) {
        switch (s) {
            case "Moon":
                moon.addPopulation(i);
                moon.setLevel();
                break;
            case "Mars":
                mars.addPopulation(i);
                mars.setLevel();
                break;
            case "Jupiter":
                jupiter.addPopulation(i);
                jupiter.setLevel();
                break;
            case "Saturn":
                saturn.addPopulation(i);
                saturn.setLevel();
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: saves the trip log to file and tells the application that the current trips are saved
    public void saveTripLog() {
        try {
            jsonWriter.open();
            jsonWriter.write(log);
            jsonWriter.close();
            isSaved = true;
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + SAVED_LOGS);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadTripLog() {
        try {
            log = jsonReader.read();
            System.out.println("Loaded from your log book!");
            lvlSetupFromSaved();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + SAVED_LOGS);
        }
    }

    // MODIFIES: this
    // EFFECTS: sets up levels of colonies based on the current state of the trip log
    private void lvlSetupFromSaved() {
        List<Trip> trips;
        trips = log.convertTripLogToList();
        for (Trip next : trips) {
            switch (next.getLocation()) {
                case "Mars":
                    mars.addPopulation(next.getDuration());
                    mars.setLevel();
                    break;
                case "Moon":
                    moon.addPopulation(next.getDuration());
                    moon.setLevel();
                    break;
                case "Jupiter":
                    jupiter.addPopulation(next.getDuration());
                    jupiter.setLevel();
                    break;
                case "Saturn":
                    saturn.addPopulation(next.getDuration());
                    saturn.setLevel();
                    break;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: deletes the given index of trip from the trip log, throws a TripDoesNotExistException if the given
    //          index is invalid for the current state of the trip log
    public void deleteTripLog(int i) throws TripDoesNotExistException {
        int index = i - 1;
        if (index < 0 || index >= log.logSize()) {
            throw new TripDoesNotExistException();
        } else {
            removePopulation(log.getTrip(index));
            log.deleteLogElement(index);
            System.out.println("Your log entry has been deleted!");
            isSaved = false;
        }
    }

    // MODIFIES: this
    // EFFECTS: removes population from a colony and set their levels according to the new population
    public void removePopulation(Trip deletedTrip) {
        switch (deletedTrip.getLocation()) {
            case "Mars":
                mars.removePopulation(deletedTrip.getDuration());
                mars.setLevel();
                break;
            case "Moon":
                moon.removePopulation(deletedTrip.getDuration());
                moon.setLevel();
                break;
            case "Jupiter":
                jupiter.removePopulation(deletedTrip.getDuration());
                jupiter.setLevel();
                break;
            case "Saturn":
                saturn.removePopulation(deletedTrip.getDuration());
                saturn.setLevel();
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: clears the trip log and resets the population and level of all the colonies back to default, then
    //          tell the application that there are changes that have not been saved yet
    public void clearTripLog() {
        log.clearTripLog();
        mars.resetPopulation();
        moon.resetPopulation();
        jupiter.resetPopulation();
        saturn.resetPopulation();
        mars.setLevel();
        moon.setLevel();
        jupiter.setLevel();
        saturn.setLevel();
        isSaved = false;
    }

    // MODIFIES: this
    // EFFECTS: removes the main panel from the frame and add a NewTripPanel, then refresh the display
    public void switchToNewTripPanel() {
        frame.getContentPane().remove(mainPanel);
        frame.getContentPane().add(new NewTripPanel(this));
        frame.revalidate();
    }

    // MODIFIES: this
    // EFFECTS: removes the main panel from the frame and add a LogPanel, then refresh the display
    public void switchToLogPanel() {
        frame.getContentPane().remove(mainPanel);
        frame.getContentPane().add(new LogPanel(this, log));
        frame.revalidate();
    }

    // MODIFIES: this
    // EFFECTS: removes the main panel from the frame and add a LevelPanel, then refresh the display
    public void switchToLevelPanel() {
        frame.getContentPane().remove(mainPanel);
        frame.getContentPane().add(new LevelPanel(this, moon, mars, jupiter, saturn));
        frame.revalidate();
    }

    // MODIFIES: this
    // EFFECTS: removes the main panel from the frame and add a SavePanel, then refresh the display
    public void switchToSavePanel() {
        saveTripLog();
        isSaved = true;
        frame.getContentPane().remove(mainPanel);
        frame.getContentPane().add(new SavePanel(this));
        frame.revalidate();
    }

    // MODIFIES: this
    // EFFECTS: removes the main panel from the frame and add a LoadPanel, then refresh the display
    public void switchToLoadPanel() {
        loadTripLog();
        frame.getContentPane().remove(mainPanel);
        frame.getContentPane().add(new LoadPanel(this));
        frame.revalidate();
    }

    // MODIFIES: this
    // EFFECTS: removes the main panel from the frame and add a QuitPanel, then refresh the display
    public void switchToQuitPanel() {
        if (!isSaved) {
            frame.getContentPane().remove(mainPanel);
            frame.getContentPane().add(new QuitPanel(this));
            frame.revalidate();
        } else {
            System.exit(0);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes the current panel and add the mainPanel back
    public void switchBackToMain() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(mainPanel);
        frame.revalidate();
    }

    // MODIFIES: this
    // EFFECTS: creates a new GUI
    public static void main(String[] args) {
        new GUI();
    }
}

