package ui;

import model.Colony;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.ArrayList;


public class LevelMenu extends JPanel implements ActionListener {
    private GUI gui;
    private Image img = Toolkit.getDefaultToolkit().createImage("src/main/ui/images/Background.gif");
    private Color notGray = new Color(54, 54, 54);
    private Color lightGray = new Color(200, 200, 200);
    private JButton exitButton;
    private Font font;

    public LevelMenu(GUI gui, Colony moon, Colony mars, Colony jupiter, Colony saturn) {
        this.gui = gui;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/ui/fonts/Nagoda.ttf"));
        } catch (Exception e) {
            System.out.println("Exception caught");
        }
        buttonsSetUp();
        labelSetUp(moon, mars, jupiter, saturn);
        add(exitButton);
        setLayout(null);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setPreferredSize(new Dimension(300, 539));
    }

    public void buttonsSetUp() {
        exitButton = new JButton("Return");
        exitButton.setBackground(notGray);
        exitButton.setBorderPainted(false);
        exitButton.setForeground(lightGray);
        exitButton.setFont(font.deriveFont(15f));
        exitButton.setFocusPainted(false);
        exitButton.setBounds(20, 490, 260, 30);
        exitButton.addActionListener(this);
    }

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
        JLabel jupiterLevel = new JLabel("Level: " + jupiter.getLevel() + "  Population: " + jupiter.getPopulation());
        JLabel saturnLevel = new JLabel("Level: " + saturn.getLevel() + "  Population: " + saturn.getPopulation());
        List<JLabel> labels = new ArrayList<>();
        labels.add(moonLevel);
        labels.add(marsLevel);
        labels.add(jupiterLevel);
        labels.add(saturnLevel);
        labelsSetUp(labels);
    }

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

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.switchBackToMain();
    }
}

