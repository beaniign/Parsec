package ui;

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
//    private static final String SAVED_LOGS = "./data/logbook.json";
//    private JsonWriter jsonWriter;
//    private JsonReader jsonReader;
//    private boolean isSaved;

    private JFrame frame;
    private JPanel mainPanel;
    private JPanel levelPanel;
    private JPanel savePanel;
    private JPanel newPanel;

//    private TripLog log;

    public GUI() {
//        isSaved = false;
//        jsonWriter = new JsonWriter(SAVED_LOGS);
//        jsonReader = new JsonReader(SAVED_LOGS);
//        initializeTripLog();
        frame = new JFrame("Parsec App");
        mainPanel = new MainMenu(this);
        levelPanel = new LevelMenu(this);
        savePanel = new SaveMenu(this);
        newPanel = new NewTripMenu(this);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/ui/images/Parsec Favicon.png"));
        frame.getContentPane().add(mainPanel);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
        new ParsecApp();
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
        frame.getContentPane().remove(mainPanel);
        frame.getContentPane().add(savePanel);
        frame.revalidate();
    }

    public void switchToLoadPanel() {
        frame.getContentPane().remove(mainPanel);
        frame.getContentPane().add(levelPanel);
        frame.revalidate();
    }

//    // MODIFIES: this
//    // EFFECTS: initializes the trip log
//    public void initializeTripLog() {
//        this.log = new TripLog();
//    }
//
//    // EFFECTS: saves the trip log to file and tells the application that the current trips are saved
//    public void saveTripLog() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(log);
//            jsonWriter.close();
//            isSaved = true;
//            System.out.println("Saved to your log book!");
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + SAVED_LOGS);
//        }
//    }

    public void switchBackToMain() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(mainPanel);
        frame.revalidate();
    }

    public void switchToQuitPanel() {
        System.exit(0);
    }
}

