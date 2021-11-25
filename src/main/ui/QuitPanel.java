package ui;

import model.Event;
import model.EventLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

// represents the panel that displays options to quit
public class QuitPanel extends JPanel implements ActionListener {
    private static final Image IMG = Toolkit.getDefaultToolkit().createImage("src/main/ui/images/Background.gif");
    private static final Color DARK_GRAY = new Color(54, 54, 54);
    private static final Color LIGHT_GRAY = new Color(200, 200, 200);
    private GUI gui;
    private Font font;
    private JButton returnButton;
    private JButton yesButton;
    private JButton noButton;

    // MODIFIES: this
    // EFFECTS: constructor for QuitPanel, calls methods to set up the visual components
    public QuitPanel(GUI gui) {
        this.gui = gui;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/ui/fonts/Nagoda.ttf"));
        } catch (Exception e) {
            System.out.println("Exception Caught");
        }
        yesButton = new JButton("Save");
        noButton = new JButton("Don't Save");
        returnButton = new JButton("Return");
        List<JButton> buttons = new ArrayList<>();
        buttons.add(yesButton);
        buttons.add(noButton);
        buttons.add(returnButton);
        buttonsSetUp(buttons);
        labelSetUp();
        setLayout(null);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setPreferredSize(new Dimension(300, 539));
    }

    // MODIFIES: this
    // EFFECTS: sets up the labels that tells the user that their changes are not saved
    public void labelSetUp() {
        JLabel label = new JLabel();
        label.setText("Your trips are not saved!");
        label.setForeground(Color.white);
        label.setFont(font.deriveFont(15f));
        label.setBounds(60, 190, 200, 100);
        add(label);
    }

    // MODIFIES: this
    // EFFECTS: sets up and styles the buttons
    public void buttonsSetUp(List<JButton> buttons) {
        int y = 270;
        for (JButton next : buttons) {
            next.setBounds(90, y, 120, 30);
            next.setBackground(DARK_GRAY);
            next.setBorderPainted(false);
            next.setForeground(LIGHT_GRAY);
            next.setFont(font.deriveFont(15f));
            next.setFocusPainted(false);
            next.addActionListener(this);
            add(next);
            y += 40;
        }

    }

    // MODIFIES: this
    // EFFECTS: sets the background of the panel
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(IMG, 0, 0, this);
    }


    // EFFECTS: perform actions when a button is pressed
    //          if the returnButton is pressed, tells the gui to switch back to the main panel
    //          if the noButton is pressed, terminates the application
    //          if the yesButton is pressed, tells the gui to save the changes then terminates the application
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnButton) {
            gui.switchBackToMain();
        }
        if (e.getSource() == noButton) {
            for (Event next : EventLog.getInstance()) {
                System.out.println(next + "\n");
            }
            System.exit(0);
        }
        if (e.getSource() == yesButton) {
            gui.saveTripLog();
            for (Event next : EventLog.getInstance()) {
                System.out.println(next + "\n");
            }
            System.exit(0);
        }
        // is how this is called okay?
        // will we have to deal with when a user closes from the top right?
        // do event & eventlog tests matter?
        // should we include the time of each event or is that not necessary?
        // should we include classes that aren't used in phase 4 in our UML diagram?
        // Persistence classes should be included as well right?
        // is the 4 correct?
        // aggregation between eventlog and event?
    }

}

