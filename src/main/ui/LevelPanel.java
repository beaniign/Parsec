package ui;

import model.Colony;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

// represents the panel to display colony levels
public class LevelPanel extends JPanel implements ActionListener {
    private static final Image IMG =
            Toolkit.getDefaultToolkit().createImage("src/main/ui/images/Background.gif");
    private final GUI gui;
    private Font font;

    // MODIFIES: this
    // EFFECTS: constructor for LevelPanel, sets up all the visual components including panel size / layout, buttons
    //          and labels based on the four colonies given
    public LevelPanel(GUI gui, Colony moon, Colony mars, Colony jupiter, Colony saturn) {
        this.gui = gui;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/ui/fonts/Nagoda.ttf"));
        } catch (Exception e) {
            System.out.println("Exception caught");
        }
        buttonSetUp();
        labelSetUp(moon, mars, jupiter, saturn);
        setLayout(null);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setPreferredSize(new Dimension(300, 539));
    }

    // MODIFIES: this
    // EFFECTS: constructs buttons and apply styling, then add it to the panel
    public void buttonSetUp() {
        JButton exitButton = new JButton("Return");
        exitButton.setOpaque(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        exitButton.setForeground(Color.white);
        exitButton.setFont(font.deriveFont(15f));
        exitButton.setFocusPainted(false);
        exitButton.setBounds(15, 490, 260, 30);
        exitButton.setHorizontalAlignment(SwingConstants.LEFT);
        exitButton.addActionListener(this);
        add(exitButton);
    }

    // EFFECTS: constructs labels and call method to apply styling (labels are put into lists for easier styling)
    public void labelSetUp(Colony moon, Colony mars, Colony jupiter, Colony saturn) {
        JLabel moonTitle = new JLabel("MOON");
        JLabel marsTitle = new JLabel("MARS");
        JLabel jupiterTitle = new JLabel("JUPITER");
        JLabel saturnTitle = new JLabel("SATURN");
        List<JLabel> titles = new ArrayList<>();
        titles.add(moonTitle);
        titles.add(marsTitle);
        titles.add(jupiterTitle);
        titles.add(saturnTitle);
        titlesSetUp(titles);
        JLabel moonLevel = new JLabel("Level: " + moon.getLevel() + "  Population: " + moon.getPopulation());
        JLabel marsLevel = new JLabel("Level: " + mars.getLevel() + "  Population: " + mars.getPopulation());
        JLabel jupiterLevel = new JLabel("Level: " + jupiter.getLevel()
                + "  Population: " + jupiter.getPopulation());
        JLabel saturnLevel = new JLabel("Level: " + saturn.getLevel()
                + "  Population: " + saturn.getPopulation());
        List<JLabel> labels = new ArrayList<>();
        labels.add(moonLevel);
        labels.add(marsLevel);
        labels.add(jupiterLevel);
        labels.add(saturnLevel);
        labelsSetUp(labels);
    }

    // EFFECTS: applies styling to title labels and add them to the panel
    public void titlesSetUp(List<JLabel> titles) {
        int y = 70;
        for (JLabel next : titles) {
            next.setForeground(Color.white);
            next.setFont(font.deriveFont(60f));
            next.setBounds(30, y, 240, 60);
            add(next);
            y += 90;
        }
    }

    // EFFECTS: applies styling to body labels and add them to the panel
    public void labelsSetUp(List<JLabel> labels) {
        int y = 120;
        for (JLabel next : labels) {
            next.setForeground(Color.white);
            next.setFont(font.deriveFont(15f));
            next.setBounds(30, y, 240, 20);
            add(next);
            y += 90;
        }
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

