package ui;

import model.Colony;
import model.Trip;
import model.TripLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

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
    private JPanel levelPanel;
    private JPanel savePanel;
    private JPanel newPanel;
    private JPanel quitPanel;

    private Colony mars;
    private Colony moon;
    private Colony jupiter;
    private Colony saturn;
    private TripLog log;


//    ParsecApp parsec;
////    private TripLog log;

    public GUI() {
        isSaved = false;
        jsonWriter = new JsonWriter(SAVED_LOGS);
        jsonReader = new JsonReader(SAVED_LOGS);
        initializeTripLog();
        initializeColonies();
        initializeTripLog();
        frame = new JFrame("Parsec App");
        mainPanel = new MainMenu(this);
        levelPanel = new LevelMenu(this);
        savePanel = new SaveMenu(this);
        newPanel = new NewTripMenu(this);
        quitPanel = new QuitMenu(this);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/ui/images/Parsec Favicon.png"));
        frame.getContentPane().add(mainPanel);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
//        parsec = new ParsecApp();
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
//        parsec.makeTrip(location, duration, note);
        // how to still use the original ui class? or can I just move all the functionality in there to this gui class?
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

    public void switchToNewPanel() {
        frame.getContentPane().remove(mainPanel);
        frame.getContentPane().add(newPanel);
        frame.revalidate();
    }

    public void switchToCheckPanel() {
        frame.getContentPane().remove(mainPanel);
        frame.getContentPane().add(levelPanel);
        frame.revalidate();
    }

    public void switchToLevelPanel() {
        frame.getContentPane().remove(mainPanel);
        frame.getContentPane().add(levelPanel);
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
        frame.getContentPane().remove(mainPanel);
        frame.getContentPane().add(levelPanel);
        frame.revalidate();
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

