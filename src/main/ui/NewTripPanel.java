package ui;

import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//https://stackoverflow.com/questions/2281937/swing-jtextfield-how-to-remove-the-border
//https://docs.oracle.com/javase/7/docs/api/java/util/Timer.html
//https://docs.oracle.com/javase/tutorial/uiswing/misc/timer.html
//https://stackoverflow.com/questions/10820033/make-a-simple-timer-in-java/14323134
public class NewTripPanel extends JPanel implements ActionListener {
    Color darkGray = new Color(54, 54, 54);
    Color lightGray = new Color(150, 150, 150);
    JButton moonButton;
    JButton marsButton;
    JButton saturnButton;
    JButton jupiterButton;
    JButton quitButton;
    JButton startButton;
    JButton returnButton;
    JTextField duration;
    JTextField note;
    JLabel currentTime;
    JLabel unexpectedTime;
    JLabel unexpectedNote;
    JLabel chooseDestination;
    JLabel noteLabel;
    String currentTrip;
    long startTime;
    GUI gui;
    Font font;
    Image bgImg = Toolkit.getDefaultToolkit().createImage("src/main/ui/images/Background.gif");
    List<JButton> buttons;
    Timer timer;

    public NewTripPanel(GUI gui) {
        menuSetUp(gui);
    }

    public void menuSetUp(GUI gui) {
        bgImg = Toolkit.getDefaultToolkit().createImage("src/main/ui/images/Background.gif");
        this.gui = gui;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/ui/fonts/Nagoda.ttf"));
        } catch (FontFormatException e) {
            System.out.println("Font not accepted");
        } catch (IOException e) {
            System.out.println("IOException Caught");
        }
        labelsSetUp();
        add(chooseDestination);
        setLayout(null);
        buttonsSetUp();
//        add(quitButton);
        for (JButton next : buttons) {
            add(next);
        }

        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setPreferredSize(new Dimension(300, 539));
    }

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
        note.setBackground(darkGray);
        note.setForeground(Color.white);
        note.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        note.setHorizontalAlignment(JTextField.CENTER);
    }

    @SuppressWarnings("methodlength")
    public void buttonsSetUp() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/ui/fonts/Nagoda.ttf"));
        } catch (FontFormatException e) {
            System.out.println("Font not accepted");
        } catch (IOException e) {
            System.out.println("IOException Caught");
        }
        quitButton = new JButton("Return");
        startButton = new JButton("Begin Journey");
        startButton.setBackground(darkGray);
        startButton.setBorderPainted(false);
        startButton.setForeground(lightGray);
        startButton.setFont(font.deriveFont(18f));
        startButton.setFocusPainted(false);
        startButton.setBounds(60, 260, 180, 40);
        startButton.addActionListener(this);
        returnButton = new JButton("Return");
        returnButton.setBackground(darkGray);
        returnButton.setBorderPainted(false);
        returnButton.setForeground(lightGray);
        returnButton.setFont(font.deriveFont(18f));
        returnButton.setFocusPainted(false);
        returnButton.setBounds(60, 310, 180, 40);
        returnButton.addActionListener(this);
        quitButton.setBackground(darkGray);
        quitButton.setBorderPainted(false);
        quitButton.setForeground(lightGray);
        quitButton.setFont(font.deriveFont(15f));
        quitButton.setFocusPainted(false);
        quitButton.setBounds(20, 490, 260, 40);
        quitButton.addActionListener(this);
        moonButton = new JButton("The Moon - Requires 15 mins");
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
            next.setBackground(darkGray);
            next.setBorderPainted(false);
            next.setForeground(lightGray);
            next.setFont(font.deriveFont(15f));
            next.setFocusPainted(false);
            next.setBounds(20, y, 260, 40);
            next.addActionListener(this);
            y -= 50;
        }
    }

    public void labelsSetUp() {
        chooseDestination = new JLabel("Choose Your Destination:");
        chooseDestination.setForeground(Color.white);
        chooseDestination.setFont(font.deriveFont(15f));
        chooseDestination.setBounds(60, 160, 250, 100);
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
        noteLabel.setForeground(lightGray);
        noteLabel.setFont(font.deriveFont(15f));
        noteLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    }

    public void newTrip() {
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
        currentTime.setBounds(85, 195, 250, 100);
        currentTime.setForeground(Color.white);
        currentTime.setFont(font.deriveFont(60f));
        currentTime.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        add(currentTime);
        startTime = System.currentTimeMillis();
        timerSetUp();
    }

    public void timerSetUp() {
        timer = new Timer(1000, this);
        timer.addActionListener(this);
        timer.start();
    }

    public void handleTripTime() {
        int minimumTime = 0; //<- using 0 as minimum just for testing
        int maximumTime = 99;
        int inputTime = Integer.parseInt(duration.getText());
        switch (currentTrip) {
            case "Mars":
//                minimumTime = 30;
                break;
            case "Jupiter":
//                minimumTime = 45;
                break;
            case "Saturn":
//                minimumTime = 60;
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

    public void handleTime() {
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

        if (timeLeft <= 0) {

            timer.stop();
            remove(currentTime);
            add(quitButton);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bgImg, 0, 0, this);
    }


    @Override
    @SuppressWarnings("methodlength")
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == quitButton) {
            remove(quitButton);
            menuSetUp(gui);
            gui.switchBackToMain();
        }
        if (e.getSource() == startButton) {
            remove(unexpectedNote);
            remove(unexpectedTime);
            handleTripTime();
        }

        if (e.getSource() == timer) {
            handleTime();
        }
        if (e.getSource() == moonButton) {
            removeAll();
            currentTrip = "Moon";
            bgImg = Toolkit.getDefaultToolkit().createImage("src/main/ui/images/Moon_before.gif");
            newTrip();
        }
        if (e.getSource() == marsButton) {
            removeAll();
            currentTrip = "Mars";
            bgImg = Toolkit.getDefaultToolkit().createImage("src/main/ui/images/Mars_before.gif");
            newTrip();
        }
        if (e.getSource() == jupiterButton) {
            removeAll();
            currentTrip = "Jupiter";
            bgImg = Toolkit.getDefaultToolkit().createImage("src/main/ui/images/Jupiter_before.gif");
            newTrip();
        }
        if (e.getSource() == saturnButton) {
            removeAll();
            currentTrip = "Saturn";
            bgImg = Toolkit.getDefaultToolkit().createImage("src/main/ui/images/Saturn_before.gif");
            newTrip();
        }
        if (e.getSource() == returnButton) {
            removeAll();
            menuSetUp(gui);
        }
    }

}
