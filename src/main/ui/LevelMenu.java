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

        moonLevel = new JLabel("Moon - Level: " + moon.getLevel() + " Population: " + moon.getPopulation());
        marsLevel = new JLabel("Mars - Level: " + mars.getLevel() + " Population: " + mars.getPopulation());
        jupiterLevel = new JLabel("Jupiter - Level: " + jupiter.getLevel()
                + " Population: " + jupiter.getPopulation());
        saturnLevel = new JLabel("Saturn - Level: " + saturn.getLevel() + " Population: " + saturn.getPopulation());
        labelStyle(moonLevel);
        labelStyle(marsLevel);
        labelStyle(jupiterLevel);
        labelStyle(saturnLevel);
        moonLevel.setBounds(30, 50, 240, 40);
        marsLevel.setBounds(30, 100, 240, 40);
        jupiterLevel.setBounds(30, 150, 240, 40);
        saturnLevel.setBounds(30, 200, 240, 40);
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

