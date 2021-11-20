package ui;

import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

// represents the panel that displays options to create a new trip
public class NewTripPanel extends JPanel implements ActionListener {
    private static final Color DARK_GRAY = new Color(54, 54, 54);
    private static final Color LIGHT_GRAY = new Color(200, 200, 200);

    private JButton moonButton;
    private JButton marsButton;
    private JButton saturnButton;
    private JButton jupiterButton;
    private JButton quitButton;
    private JButton startButton;
    private JButton returnButton;

    private JTextField duration;
    private JTextField note;

    private JLabel currentTime;
    private JLabel unexpectedTime;
    private JLabel unexpectedNote;
    private JLabel noteLabel;

    private long startTime;
    private Timer timer;
    private String currentTrip;
    private GUI gui;
    private Font font;
    private Image bgImg;
    private List<JButton> buttons;

    // EFFECTS: constructor
    public NewTripPanel(GUI gui) {
        menuSetUp(gui);
    }

    // MODIFIES: this
    // EFFECTS: sets up the size of the panel and calls methods to set up components of the panel such as buttons and
    //          labels
    public void menuSetUp(GUI gui) {
        bgImg = Toolkit.getDefaultToolkit().createImage("src/main/ui/images/Choose_Destination.gif");
        this.gui = gui;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/ui/fonts/Nagoda.ttf"));
        } catch (Exception e) {
            System.out.println("Exception Caught");
        }
        labelsSetUp();
        setLayout(null);
        buttonsSetUp();
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setPreferredSize(new Dimension(300, 539));
        add(quitButton);
    }

    // MODIFIES: this
    // EFFECTS: creates text fields to read user input and styles them
    public void textFieldSetUp() {
        duration = new JTextField("15");
        duration.setBounds(60, 70, 180, 100);
        duration.setFont(font.deriveFont(100f));
        duration.setOpaque(false);
        duration.setForeground(Color.white);
        duration.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        duration.setHorizontalAlignment(JTextField.CENTER);
        note = new JTextField("");
        note.setBounds(60, 190, 180, 60);
        note.setFont(font.deriveFont(25f));
        note.setBackground(DARK_GRAY);
        note.setForeground(Color.white);
        note.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        note.setHorizontalAlignment(JTextField.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: constructs and styles the buttons
    @SuppressWarnings("methodlength")
    public void buttonsSetUp() {
        quitButton = new JButton("Return");
        startButton = new JButton("Begin Journey");
        startButton.setBackground(DARK_GRAY);
        startButton.setBorderPainted(false);
        startButton.setForeground(LIGHT_GRAY);
        startButton.setFont(font.deriveFont(18f));
        startButton.setFocusPainted(false);
        startButton.setBounds(60, 260, 180, 40);
        startButton.addActionListener(this);
        returnButton = new JButton("Return");
        returnButton.setBackground(DARK_GRAY);
        returnButton.setBorderPainted(false);
        returnButton.setForeground(LIGHT_GRAY);
        returnButton.setFont(font.deriveFont(17f));
        returnButton.setFocusPainted(false);
        returnButton.setBounds(60, 310, 180, 40);
        returnButton.addActionListener(this);
        quitButton.setBackground(DARK_GRAY);
        quitButton.setBorderPainted(false);
        quitButton.setForeground(LIGHT_GRAY);
        quitButton.setFont(font.deriveFont(17f));
        quitButton.setFocusPainted(false);
        quitButton.setBounds(20, 480, 260, 40);
        quitButton.addActionListener(this);
        moonButton = new JButton("Moon - Requires 15 mins");
        marsButton = new JButton("Mars - Requires 30 mins");
        jupiterButton = new JButton("Jupiter - Requires 45 mins");
        saturnButton = new JButton("Saturn - Requires 60 mins");
        buttons = new ArrayList<>();
        buttons.add(saturnButton);
        buttons.add(jupiterButton);
        buttons.add(marsButton);
        buttons.add(moonButton);

        int y = 380;
        for (JButton next : buttons) {
            next.setBackground(DARK_GRAY);
            next.setBorderPainted(false);
            next.setForeground(LIGHT_GRAY);
            next.setFont(font.deriveFont(17f));
            next.setFocusPainted(false);
            next.setBounds(20, y, 260, 60);
            next.addActionListener(this);
            add(next);
            y -= 70;
        }
    }

    // MODIFIES: this
    // EFFECTS: constructs and styles the labels
    public void labelsSetUp() {
        unexpectedTime = new JLabel("Time not allowed!");
        unexpectedTime.setBounds(85, 20, 250, 20);
        unexpectedTime.setForeground(Color.red);
        unexpectedTime.setFont(font.deriveFont(15f));
        unexpectedTime.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        unexpectedNote = new JLabel("Note too long! (Max 8 Char)");
        unexpectedNote.setBounds(50, 40, 250, 20);
        unexpectedNote.setForeground(Color.red);
        unexpectedNote.setFont(font.deriveFont(15f));
        unexpectedNote.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        noteLabel = new JLabel("Enter Trip Notes Below:");
        noteLabel.setBounds(65, 125, 250, 100);
        noteLabel.setForeground(LIGHT_GRAY);
        noteLabel.setFont(font.deriveFont(15f));
        noteLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    }

    // MODIFIES: this
    // EFFECTS: adds the components needed to form the new trip options menu, sets the default time based on location
    public void setUpNewTripOptions() {
        textFieldSetUp();
        switch (currentTrip) {
            case "Moon":
                duration.setText("15");
                break;
            case "Mars":
                duration.setText("30");
                break;
            case "Jupiter":
                duration.setText("45");
                break;
            case "Saturn":
                duration.setText("60");
                break;
        }
        add(duration);
        add(note);
        add(noteLabel);
        add(startButton);
        add(returnButton);
    }

    // MODIFIES: this
    // EFFECTS: adds trip timer on screen, switches background graphics based on the location, makes a call to start
    //          a timer
    @SuppressWarnings("methodlength")
    public void startTrip() {
        removeAll();
        gui.createNewTrip(Integer.parseInt(duration.getText()), currentTrip, note.getText());
        switch (currentTrip) {
            case "Mars":
                bgImg = Toolkit.getDefaultToolkit().createImage("src/main/ui/images/Spaceship_2.gif");
                break;
            case "Jupiter":
                bgImg = Toolkit.getDefaultToolkit().createImage("src/main/ui/images/Spaceship_3.gif");
                break;
            case "Saturn":
                bgImg = Toolkit.getDefaultToolkit().createImage("src/main/ui/images/Spaceship_4.gif");
                break;
            default:
                bgImg = Toolkit.getDefaultToolkit().createImage("src/main/ui/images/Spaceship_1.gif");
                break;
        }
        currentTime = new JLabel(duration.getText() + ":00");
        currentTime.setBounds(25, 195, 250, 100);
        currentTime.setHorizontalAlignment(SwingConstants.CENTER);
        currentTime.setForeground(Color.white);
        currentTime.setFont(font.deriveFont(80f));
        currentTime.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        add(currentTime);
        startTime = System.currentTimeMillis();
        timerSetUp();
    }

    // MODIFIES: this
    // EFFECTS: constructs a timer and starts the timer
    public void timerSetUp() {
        timer = new Timer(1000, this);
        timer.addActionListener(this);
        timer.start();
    }

    // EFFECTS: handles the time and note given by user, checks if they adhere to the rules based on the chosen
    //          location, if all conditions are meant, start the trip
    public void handleTripInput() {
//        int minimumTime = 0; //<- use 0 as minimum for testing
        int minimumTime = 15;
        int maximumTime = 99;
        int inputTime = Integer.parseInt(duration.getText());
        switch (currentTrip) {
            case "Mars":
                minimumTime = 30;
                break;
            case "Jupiter":
                minimumTime = 45;
                break;
            case "Saturn":
                minimumTime = 60;
                break;
        }
        if (inputTime < minimumTime || inputTime > maximumTime) {
            add(unexpectedTime);
        }
        if ((int) note.getText().chars().count() > 8) {
            add(unexpectedNote);
        } else if (inputTime >= minimumTime && inputTime <= maximumTime) {
            startTrip();
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the timer based on the time, stop the time if the time left is less than or equal to 0
    public void updateTime() {
        int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        int timeLeft = (Integer.parseInt(duration.getText()) * 60000) - elapsedTime;
        long secondsLeft = timeLeft / 1000;
        long secondsDisplay = secondsLeft % 60;
        long minutesLeft = secondsLeft / 60;
//            System.out.println(timeLeft);  <- not needed for GUI, just sout'ing to check
        if (secondsDisplay <= 9) {
            currentTime.setText(minutesLeft + ":0" + (timeLeft / 1000) % 60);

        } else {
            currentTime.setText(minutesLeft + ":" + (timeLeft / 1000) % 60);
        }
        currentTime.setHorizontalAlignment(SwingConstants.CENTER);
        if (timeLeft <= 0) {
            timer.stop();
            remove(currentTime);
            add(quitButton);
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the background of the panel
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bgImg, 0, 0, this);
    }


    // EFFECTS: perform actions when a button is pressed
    //          if the quitButton is pressed, tells the gui to switch to the main panel
    //          if the startButton is pressed, calls handleTripInput to check if user inputs are valid before starting
    //          if the moonButton is pressed, sets the current trip to moon, and sets up the trip options menu
    //          if the marsButton is pressed, sets the current trip to mars, and sets up the trip options menu
    //          if the jupiterButton is pressed, sets the current trip to jupiter, and sets up the trip options menu
    //          if the saturnButton is pressed, sets the current trip to saturn, and sets up the trip options menu
    //          if the returnButton is pressed, reloads the display components (note this is used within the
    //          confirm-delete and confirm-clear menu, thus returns user back to the new trip panel)
    //          if action is performed by the timer (i.e. time ticks), updates the timer on screen
    @Override
    @SuppressWarnings("methodlength")
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == quitButton) {
            gui.switchBackToMain();
        }
        if (e.getSource() == startButton) {
            remove(unexpectedNote);
            remove(unexpectedTime);
            handleTripInput();
        }
        if (e.getSource() == timer) {
            updateTime();
        }
        if (e.getSource() == moonButton) {
            removeAll();
            currentTrip = "Moon";
            bgImg = Toolkit.getDefaultToolkit().createImage("src/main/ui/images/Moon_before.gif");
            setUpNewTripOptions();
        }
        if (e.getSource() == marsButton) {
            removeAll();
            currentTrip = "Mars";
            bgImg = Toolkit.getDefaultToolkit().createImage("src/main/ui/images/Mars_before.gif");
            setUpNewTripOptions();
        }
        if (e.getSource() == jupiterButton) {
            removeAll();
            currentTrip = "Jupiter";
            bgImg = Toolkit.getDefaultToolkit().createImage("src/main/ui/images/Jupiter_before.gif");
            setUpNewTripOptions();
        }
        if (e.getSource() == saturnButton) {
            removeAll();
            currentTrip = "Saturn";
            bgImg = Toolkit.getDefaultToolkit().createImage("src/main/ui/images/Saturn_before.gif");
            setUpNewTripOptions();
        }
        if (e.getSource() == returnButton) {
            removeAll();
            menuSetUp(gui);
        }
    }

}
