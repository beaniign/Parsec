package ui;

import model.Colony;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class LevelMenu extends JPanel implements ActionListener {
    GUI gui;
    Font font;
    Image img = Toolkit.getDefaultToolkit().createImage("src/main/ui/images/Background.gif");
    Color notGray = new Color(54, 54, 54);
    Color lightGray = new Color(150, 150, 150);
    JButton exitButton;
    Colony moon;
    Colony mars;
    Colony jupiter;
    Colony saturn;

    JLabel moonLevel;
    JLabel marsLevel;
    JLabel jupiterLevel;
    JLabel saturnLevel;


    public LevelMenu(GUI gui, Colony moon, Colony mars, Colony jupiter, Colony saturn) {
        this.moon = moon;
        this.mars = mars;
        this.jupiter = jupiter;
        this.saturn = saturn;
        this.gui = gui;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/ui/fonts/Nagoda.ttf"));
        } catch (Exception e) {
            System.out.println("Exception caught");
        }
        buttonsSetUp();
        labelSetUp();
        add(moonLevel);
        add(marsLevel);
        add(jupiterLevel);
        add(saturnLevel);
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

    public void labelSetUp() {
        JLabel moonTitle = new JLabel("MOON");
        labelTitleStyle(moonTitle);
        moonTitle.setBounds(30, 30, 240, 60);
        add(moonTitle);
        JLabel marsTitle = new JLabel("MARS");
        labelTitleStyle(marsTitle);
        marsTitle.setBounds(30, 120, 240, 60);
        add(marsTitle);
        JLabel jupiterTitle = new JLabel("JUPITER");
        labelTitleStyle(jupiterTitle);
        jupiterTitle.setBounds(30, 210, 240, 60);
        add(jupiterTitle);
        JLabel saturnTitle = new JLabel("SATURN");
        labelTitleStyle(saturnTitle);
        saturnTitle.setBounds(30, 300, 240, 60);
        add(saturnTitle);
        moonLevel = new JLabel("Level: " + moon.getLevel() + "  Population: " + moon.getPopulation());
        marsLevel = new JLabel("Level: " + mars.getLevel() + "  Population: " + mars.getPopulation());
        jupiterLevel = new JLabel("Level: " + jupiter.getLevel() + "  Population: " + jupiter.getPopulation());
        saturnLevel = new JLabel("Level: " + saturn.getLevel() + "  Population: " + saturn.getPopulation());
        labelStyle(moonLevel);
        labelStyle(marsLevel);
        labelStyle(jupiterLevel);
        labelStyle(saturnLevel);
        moonLevel.setBounds(30, 80, 240, 20);
        marsLevel.setBounds(30, 170, 240, 20);
        jupiterLevel.setBounds(30, 260, 240, 20);
        saturnLevel.setBounds(30, 350, 240, 20);
    }

    public void labelTitleStyle(JLabel label) {
        label.setForeground(Color.white);
        label.setFont(font.deriveFont(60f));
    }
    public void labelStyle(JLabel label) {
        label.setForeground(Color.white);
        label.setFont(font.deriveFont(15f));
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

