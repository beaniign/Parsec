package ui;

import model.Trip;
import model.TripLog;
import model.exception.TripDoesNotExistException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;


public class LogPanel extends JPanel implements ActionListener {
    private GUI gui;
    private Font font;
    private Image img = Toolkit.getDefaultToolkit().createImage("src/main/ui/images/Background.gif");
    private Color darkGray = new Color(54, 54, 54);
    private Color lightGray = new Color(200, 200, 200);
    JButton deleteButton;
    JButton clearButton;
    JButton exitButton;
    JButton confirmDeleteButton;
    JButton confirmClearButton;
    JButton returnButton;
    JTextField indexToDelete;
    private int index;
    private TripLog trips;

    public LogPanel(GUI gui, TripLog tripLog) {
        this.trips = tripLog;
        this.gui = gui;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/ui/fonts/Nagoda.ttf"));
        } catch (Exception e) {
            System.out.println("Exception Caught");
        }
        setUp();
    }

    public void setUp() {
        labelSetUp(trips);
        buttonsSetUp();
        setLayout(null);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setPreferredSize(new Dimension(300, 539));
    }

    public void labelSetUp(TripLog tripLog) {
        List<Trip> trips = tripLog.convertTripLogToList();
        if (trips.isEmpty()) {
            JLabel label = new JLabel("Your Logs are Empty!");
            label.setForeground(Color.white);
            label.setFont(font.deriveFont(20f));
            label.setBounds(10, 240, 280, 50);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            add(label);
        } else {
            int y = 40;
            for (Trip next : trips) {
                JLabel label =
                        new JLabel("Trip #" + (trips.indexOf(next) + 1) + " - " + next.getLocation().toUpperCase()
                                + " - " + next.getDuration() + "mins - Note: " + next.getNote());
                label.setForeground(Color.white);
                label.setFont(font.deriveFont(12f));
                label.setBounds(20, y, 280, 15);
                add(label);
                y += 25;
            }
        }
    }

    public void buttonsSetUp() {
        deleteButton = new JButton("Delete");
        deleteButton.setBounds(20, 449, 125, 30);
        buttonStyle(deleteButton);
        clearButton = new JButton("Clear");
        clearButton.setBounds(155, 449, 125, 30);
        buttonStyle(clearButton);
        exitButton = new JButton("Return to Main Menu");
        exitButton.setBounds(20, 489, 260, 30);
        buttonStyle(exitButton);
    }

    public void buttonStyle(JButton button) {
        button.setBackground(darkGray);
        button.setBorderPainted(false);
        button.setForeground(lightGray);
        button.setFont(font.deriveFont(15f));
        button.setFocusPainted(false);
        button.addActionListener(this);
        add(button);
    }

    public void setUpClearOptions() {
        addWarning();
        addReturn();
        confirmClearButton = new JButton("Clear");
        confirmClearButton.setBackground(darkGray);
        confirmClearButton.setBorderPainted(false);
        confirmClearButton.setForeground(lightGray);
        confirmClearButton.setFont(font.deriveFont(15f));
        confirmClearButton.setFocusPainted(false);
        confirmClearButton.addActionListener(this);
        confirmClearButton.setBounds(20, 300, 260, 30);
        add(confirmClearButton);
    }

    public void setUpDeleteOptions() {
        addWarning();
        addReturn();
        indexToDelete = new JTextField("Enter # of Trip to Delete Here");
        indexToDelete.setBounds(20, 230, 260, 60);
        indexToDelete.setFont(font.deriveFont(15f));
        indexToDelete.setBackground(darkGray);
        indexToDelete.setForeground(Color.white);
        indexToDelete.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        indexToDelete.setHorizontalAlignment(JTextField.CENTER);
        confirmDeleteButton = new JButton("Delete");
        confirmDeleteButton.setBackground(darkGray);
        confirmDeleteButton.setBorderPainted(false);
        confirmDeleteButton.setForeground(lightGray);
        confirmDeleteButton.setFont(font.deriveFont(15f));
        confirmDeleteButton.setFocusPainted(false);
        confirmDeleteButton.addActionListener(this);
        confirmDeleteButton.setBounds(20, 300, 260, 30);
        add(confirmDeleteButton);
        add(indexToDelete);
    }

    public void addWarning() {
        JLabel warning = new JLabel("!WARNING!");
        warning.setForeground(Color.red);
        warning.setFont(font.deriveFont(45f));
        warning.setBounds(30, 100, 240, 60);
        add(warning);
        warning.setHorizontalAlignment(JTextField.CENTER);
        JLabel warningBody1 = new JLabel("This action will remove the population &");
        JLabel warningBody2 = new JLabel("level(s) gained with the removed trip(s)");
        warningBody1.setForeground(Color.red);
        warningBody1.setFont(font.deriveFont(12f));
        warningBody1.setBounds(30, 150, 240, 20);
        add(warningBody1);
        warningBody2.setForeground(Color.red);
        warningBody2.setFont(font.deriveFont(12f));
        warningBody2.setBounds(30, 165, 240, 20);
        warningBody2.setHorizontalAlignment(JTextField.CENTER);
        warningBody1.setHorizontalAlignment(JTextField.CENTER);
        add(warningBody2);
    }

    public void addReturn() {
        returnButton = new JButton("Return");
        returnButton.setBackground(darkGray);
        returnButton.setBorderPainted(false);
        returnButton.setForeground(lightGray);
        returnButton.setFont(font.deriveFont(15f));
        returnButton.setFocusPainted(false);
        returnButton.addActionListener(this);
        returnButton.setBounds(20, 340, 260, 30);
        add(returnButton);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            gui.switchBackToMain();
        }
        if (e.getSource() == deleteButton) {
            removeAll();
            setUpDeleteOptions();
        }
        if (e.getSource() == confirmDeleteButton) {
            if (trips.isEmptyLog()) {
                JLabel label = new JLabel("Your Logs are empty!");
                label.setForeground(Color.red);
                label.setFont(font.deriveFont(15f));
                label.setBounds(30, 380, 240, 60);
                label.setHorizontalAlignment(JTextField.CENTER);
                add(label);
            } else {
                try {
                    index = Integer.parseInt(indexToDelete.getText());
                    gui.deleteTripLog(index);
                    removeAll();
                    setUp();
                } catch (NumberFormatException | TripDoesNotExistException exception) {
                    JLabel label = new JLabel("Please Enter a Valid #!");
                    label.setForeground(Color.red);
                    label.setFont(font.deriveFont(15f));
                    label.setBounds(30, 400, 240, 60);
                    label.setHorizontalAlignment(JTextField.CENTER);
                    add(label);
                }
            }
        }
        if (e.getSource() == returnButton) {
            removeAll();
            setUp();
        }
        if (e.getSource() == clearButton) {
            removeAll();
            setUpClearOptions();
        }
        if (e.getSource() == confirmClearButton) {
            gui.clearTripLog();
            removeAll();
            setUp();
        }
    }

}

