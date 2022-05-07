package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

// represents the panel to display load trips from saved files
public class LoadPanel extends JPanel implements ActionListener {
    private static final Color DARK_GRAY = new Color(54, 54, 54);
    private static final Color LIGHT_GRAY = new Color(200, 200, 200);
    private static final Image IMG =
            Toolkit.getDefaultToolkit().createImage("src/main/ui/images/Background.gif");
    private GUI gui;
    private Font font;

    // MODIFIES: this
    // EFFECTS: constructor for LoadPanel, sets up visual components including panel size, button and label
    public LoadPanel(GUI gui) {
        this.gui = gui;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/ui/fonts/Nagoda.ttf"));
        } catch (Exception e) {
            System.out.println("Exception caught");
        }
        labelSetUp();
        buttonSetUp();
        setLayout(null);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setPreferredSize(new Dimension(300, 539));
    }

    // EFFECTS: constructs label and apply styling
    public void labelSetUp() {
        JLabel label = new JLabel("Trip Logs Loaded!");
        label.setForeground(Color.white);
        label.setFont(font.deriveFont(20f));
        label.setBounds(65, 200, 200, 100);
        add(label);
    }

    // EFFECTS: constructs button and apply styling
    public void buttonSetUp() {
        JButton exitButton = new JButton("Okay!");
        exitButton.setBackground(DARK_GRAY);
        exitButton.setForeground(LIGHT_GRAY);
        exitButton.setFont(font.deriveFont(15f));
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);
        exitButton.setBounds(100, 280, 100, 30);
        exitButton.addActionListener(this);
        add(exitButton);
    }

    // MODIFIES: this
    // EFFECTS: sets the background of the panel
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(IMG, 0, 0, this);
    }

    // EFFECTS: perform actions when a button is pressed, in this case tells the gui to switch back to the main panel
    @Override
    public void actionPerformed(ActionEvent e) {
        gui.switchBackToMain();
    }

}

