package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// represents the main menu panel
public class MainPanel extends JPanel implements ActionListener {
    private static final Image IMG = Toolkit.getDefaultToolkit().createImage(
            "src/main/ui/images/Main_Background.gif");
    private static GUI GUI;

    private JButton newTripButton;
    private JButton logButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton levelButton;
    private JButton exitButton;

    private Font font;
    private List<JButton> buttons;

    // MODIFIES: this
    // EFFECTS: constructor for MainPanel
    public MainPanel(GUI gui) {
        GUI = gui;
        setLayout(null);
        buttonsSetUp();
        for (JButton next : buttons) {
            add(next);
        }
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setPreferredSize(new Dimension(300, 539));
    }

    // MODIFIES: this
    // EFFECTS: sets up ands styles buttons in the main menu
    @SuppressWarnings("methodlength")
    public void buttonsSetUp() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/ui/fonts/Nagoda.ttf"));
        } catch (FontFormatException e) {
            System.out.println("Font not accepted");
        } catch (IOException e) {
            System.out.println("IOException Caught");
        }
        newTripButton = new JButton("New Trip");
        logButton = new JButton("Trip Logs");
        levelButton = new JButton("Colony Levels");
        saveButton = new JButton("Save Changes");
        loadButton = new JButton("Load Trips");
        exitButton = new JButton("Quit");
        buttons = new ArrayList<>();
        buttons.add(exitButton);
        buttons.add(loadButton);
        buttons.add(saveButton);
        buttons.add(levelButton);
        buttons.add(logButton);
        buttons.add(newTripButton);
        int y = 385;
        for (JButton next : buttons) {
            next.setBorderPainted(false);
            next.setForeground(Color.white);
            next.setFont(font.deriveFont(22f));
            next.setFocusPainted(false);
            next.setOpaque(false);
            next.setHorizontalAlignment(SwingConstants.LEFT);
            next.setContentAreaFilled(false);
            next.setBounds(20, y, 260, 40);
            next.addActionListener(this);
            y -= 30;
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the background of the panel
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(IMG, 0, 0, this);
    }

    // EFFECTS: perform actions when a button is pressed
    //          if the newTripButton is pressed, tells the gui to switch to a NewTripPanel
    //          if the logButton is pressed, tells the gui to switch to a LogPanel
    //          if the levelButton is pressed, tells the gui to switch to a LevelPanel
    //          if the saveButton is pressed, tells the gui to switch to a SavePanel
    //          if the loadButton is pressed, tells the gui to switch to a LoadPanel
    //          if the exitButton is pressed, tells the gui to switch to a QuitPanel
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newTripButton) {
            GUI.switchToNewTripPanel();
        }
        if (e.getSource() == logButton) {
            GUI.switchToLogPanel();
        }
        if (e.getSource() == levelButton) {
            GUI.switchToLevelPanel();
        }
        if (e.getSource() == saveButton) {
            GUI.switchToSavePanel();
        }
        if (e.getSource() == loadButton) {
            GUI.switchToLoadPanel();
        }
        if (e.getSource() == exitButton) {
            GUI.switchToQuitPanel();
        }
    }
}
