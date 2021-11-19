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

// https://www.tutorialspoint.com/how-to-set-the-location-of-a-button-anywhere-in-jframe
// https://stackoverflow.com/questions/6593322/why-does-the-jframe-setsize-method-not-set-the-size-correctly
// https://stackoverflow.com/questions/20462167/increasing-font-size-in-a-jbutton
// https://stackoverflow.com/questions/14627223/how-to-change-a-jbutton-color-on-mouse-pressed
// https://www.codejava.net/java-se/swing/jlabel-basic-tutorial-and-examples
// https://stackoverflow.com/questions/12998604/adding-fonts-to-swing-application-and-include-in-package
// https://www.behance.net/gallery/128359661/Nagoda-Free-Modern-Display-Font?tracking_source=search_projects_recommended%7Cfree%20fonts
public class GUI {
    private static final String SAVED_LOGS = "./data/logbook.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private boolean isSaved;

    private JFrame frame;
    private JPanel mainPanel;
    private JPanel savePanel;
    private JPanel newPanel;
    private JPanel quitPanel;
    private JPanel loadPanel;
    private JPanel logPanel;

    private Colony mars;
    private Colony moon;
    private Colony jupiter;
    private Colony saturn;
    private TripLog log;


    public GUI() {
        initializeJson();
        initializeTripLog();
        initializeColonies();
        initializeTripLog();
        initializePanels();
        initializeFrame();
    }

    public void initializeJson() {
        isSaved = true;
        jsonWriter = new JsonWriter(SAVED_LOGS);
        jsonReader = new JsonReader(SAVED_LOGS);
    }

    public void initializeFrame() {
        frame = new JFrame("Parsec");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/ui/images/Parsec Favicon.png"));
        frame.getContentPane().add(mainPanel);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    public void initializePanels() {
        mainPanel = new MainPanel(this);
        savePanel = new SavePanel(this);
        newPanel = new NewTripPanel(this);
        quitPanel = new QuitPanel(this);
        loadPanel = new LoadPanel(this);
        logPanel = new LogPanel(this, log);
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


    public void createNewTrip(int duration, String location, String note) {
        Trip newTrip = new Trip(duration, location, note);
        setLocation(location, duration);
        log.addTrip(newTrip);
        isSaved = false;
    }

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

    public void switchToNewPanel() {
        frame.getContentPane().remove(mainPanel);
        frame.getContentPane().add(new NewTripPanel(this));
        frame.revalidate();
    }

    public void switchToLogPanel() {
        frame.getContentPane().remove(mainPanel);
        frame.getContentPane().add(new LogPanel(this, log));
        frame.revalidate();
    }

    public void switchToLevelPanel() {
        frame.getContentPane().remove(mainPanel);
        frame.getContentPane().add(new LevelMenu(this, moon, mars, jupiter, saturn));
        frame.revalidate();
    }

    public void switchToSavePanel() {
        saveTripLog();
        isSaved = true;
        frame.getContentPane().remove(mainPanel);
        frame.getContentPane().add(savePanel);
        frame.revalidate();
    }

    public void switchToLoadPanel() {
        loadTripLog();
        frame.getContentPane().remove(mainPanel);
        frame.getContentPane().add(loadPanel);
        frame.revalidate();
    }


    public void switchBackToMain() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(mainPanel);
        frame.revalidate();
    }

    public void switchToQuitPanel() {

        if (!isSaved) {
            frame.getContentPane().remove(mainPanel);
            frame.getContentPane().add(quitPanel);
            frame.revalidate();
        } else {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new GUI();
    }
}

