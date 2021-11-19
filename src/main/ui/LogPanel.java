package ui;

import model.Trip;
import model.TripLog;
import model.exception.TripDoesNotExistException;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

// represents the panel to display log trips from saved files
public class LogPanel extends JPanel implements ActionListener {
    private static final Image IMG =
            Toolkit.getDefaultToolkit().createImage("src/main/ui/images/Background.gif");
    private static final Color DARK_GRAY = new Color(54, 54, 54);
    private static final Color LIGHT_GRAY = new Color(200, 200, 200);
    private GUI gui;
    private Font font;
    private JPanel displayPanel;
    private JButton deleteButton;
    private JButton clearButton;
    private JButton exitButton;
    private JButton confirmDeleteButton;
    private JButton confirmClearButton;
    private JButton returnButton;
    private JTextField indexToDelete;
    private JScrollPane scrollPane;
    private TripLog trips;

    // MODIFIES: this
    // EFFECTS: constructor for LogPanel
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

    // MODIFIES: this
    // EFFECTS: sets up visual components including panel size, button and label
    public void setUp() {
        scrollSetUp();
        labelSetUp(trips);
        buttonsSetUp();
        setLayout(null);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setPreferredSize(new Dimension(300, 539));
    }

    // MODIFIES: this
    // EFFECTS: sets up the scroll pane containing a panel which then contains the trips' information
    @SuppressWarnings("methodlength")
    public void scrollSetUp() {
        displayPanel = new JPanel();
        displayPanel.setPreferredSize(new Dimension(0, (trips.logSize() * 18 + 5)));
        displayPanel.setBackground(Color.darkGray);
        scrollPane = new JScrollPane(displayPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(20, 20, 260, 420);
        scrollPane.setBorder(BorderFactory.createLineBorder(DARK_GRAY));
        scrollPane.setBackground(DARK_GRAY);
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(140, 140, 140);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = super.createDecreaseButton(orientation);
                button.setVisible(false);
                return button;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = super.createIncreaseButton(orientation);
                button.setVisible(false);
                return button;
            }
        });
        scrollPane.getVerticalScrollBar().setBackground(DARK_GRAY);
    }

    // MODIFIES: this
    // EFFECTS: sets up the labels that contain the trips' information
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
            int y = 30;
            for (Trip next : trips) {
                JLabel label =
                        new JLabel("Trip #" + (trips.indexOf(next) + 1) + " - " + next.getLocation().toUpperCase()
                                + " - " + next.getDuration() + "mins - " + next.getNote());
                label.setForeground(Color.white);
                label.setFont(font.deriveFont(12f));
                label.setBounds(20, y, 200, 15);
                displayPanel.add(label);
                y += 51;
            }
            add(scrollPane);
        }
    }

    // MODIFIES: this
    // EFFECTS: sets up delete, clear, and exit buttons, then makes a call to style them
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

    // EFFECTS: styles the given button
    public void buttonStyle(JButton button) {
        button.setBackground(DARK_GRAY);
        button.setBorderPainted(false);
        button.setForeground(LIGHT_GRAY);
        button.setFont(font.deriveFont(15f));
        button.setFocusPainted(false);
        button.addActionListener(this);
        add(button);
    }

    // MODIFIES: this
    // EFFECTS: sets up the screen to confirm the user's intent to clear the trip log and adds warnings
    public void setUpClearOptions() {
        addWarning();
        addReturn();
        confirmClearButton = new JButton("Clear");
        confirmClearButton.setBackground(DARK_GRAY);
        confirmClearButton.setBorderPainted(false);
        confirmClearButton.setForeground(LIGHT_GRAY);
        confirmClearButton.setFont(font.deriveFont(15f));
        confirmClearButton.setFocusPainted(false);
        confirmClearButton.addActionListener(this);
        confirmClearButton.setBounds(20, 300, 260, 30);
        add(confirmClearButton);
    }

    // MODIFIES: this
    // EFFECTS: sets up the screen to confirm the user's intent to delete a trip and adds warnings
    public void setUpDeleteOptions() {
        addWarning();
        addReturn();
        indexToDelete = new JTextField("Enter # of Trip to Delete Here");
        indexToDelete.setBounds(20, 230, 260, 60);
        indexToDelete.setFont(font.deriveFont(15f));
        indexToDelete.setBackground(DARK_GRAY);
        indexToDelete.setForeground(Color.white);
        indexToDelete.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        indexToDelete.setHorizontalAlignment(JTextField.CENTER);
        confirmDeleteButton = new JButton("Delete");
        confirmDeleteButton.setBackground(DARK_GRAY);
        confirmDeleteButton.setBorderPainted(false);
        confirmDeleteButton.setForeground(LIGHT_GRAY);
        confirmDeleteButton.setFont(font.deriveFont(15f));
        confirmDeleteButton.setFocusPainted(false);
        confirmDeleteButton.addActionListener(this);
        confirmDeleteButton.setBounds(20, 300, 260, 30);
        add(confirmDeleteButton);
        add(indexToDelete);
    }

    // EFFECTS: adds warning labels to the panel
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

    // MODIFIES: this
    // EFFECTS: creates and adds a return button
    public void addReturn() {
        returnButton = new JButton("Return");
        returnButton.setBackground(DARK_GRAY);
        returnButton.setBorderPainted(false);
        returnButton.setForeground(LIGHT_GRAY);
        returnButton.setFont(font.deriveFont(15f));
        returnButton.setFocusPainted(false);
        returnButton.addActionListener(this);
        returnButton.setBounds(20, 340, 260, 30);
        add(returnButton);
    }

    // MODIFIES: this
    // EFFECTS: sets the background of the panel
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(IMG, 0, 0, this);
    }

    // EFFECTS: perform actions when a button is pressed
    //          if the exit button is pressed, tells the gui to switch back to the main panel
    //          if the delete button is pressed, sets up delete options to confirm user intent
    //          if the confirm delete button is pressed, checks if the given index is valid (throws
    //          exceptions if it isn't valid i.e. if the index number DNE in the log, or is negative), then deletes a
    //          trip from the trip log via a call mto gui and resets the panel
    //          if the return button is pressed, refreshes the display components (note this is used within the
    //          confirm-delete and confirm-clear menu, thus returns user back to the log panel)
    //          if the clear button is pressed, sets up clear options to confirm user intent
    //          if the confirm clear button is pressed, clears the trip log via a call to gui, then resets the panel
    @SuppressWarnings("methodlength")
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
                    int index = Integer.parseInt(indexToDelete.getText());
                    gui.deleteTripLog(index);
                    removeAll();
                    setUp();
                    scrollPane.revalidate();
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
            scrollPane.revalidate();
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

