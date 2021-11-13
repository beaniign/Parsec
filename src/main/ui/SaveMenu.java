package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SaveMenu extends JPanel implements ActionListener {
    GUI gui;
    Font font;
    Image img = Toolkit.getDefaultToolkit().createImage("src/main/ui/images/Background.gif");
    Color notGray = new Color(54, 54, 54);
    Color lightGray = new Color(150, 150, 150);
    JButton exitButton;


    public SaveMenu(GUI gui) {
        this.gui = gui;
        JLabel label = new JLabel();
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/ui/fonts/Nagoda.ttf"));
        } catch (FontFormatException e) {
            System.out.println("Font not accepted");
        } catch (IOException e) {
            System.out.println("IOException Caught");
        }
        label.setText("Saved to your Log Book!");
        label.setForeground(Color.white);
        label.setFont(font.deriveFont(15f));
        label.setBounds(60, 200, 200, 100);
        buttonsSetUp();
        add(label);
        add(exitButton);
        setLayout(null);

        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setPreferredSize(new Dimension(300, 539));

//        gui.saveTripLog();

    }

    public void buttonsSetUp() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/ui/fonts/Nagoda.ttf"));
        } catch (FontFormatException e) {
            System.out.println("Font not accepted");
        } catch (IOException e) {
            System.out.println("IOException Caught");
        }
        exitButton = new JButton("Okay!");
        exitButton.setBackground(notGray);
        exitButton.setBorderPainted(false);
        exitButton.setForeground(lightGray);
        exitButton.setFont(font.deriveFont(15f));
        exitButton.setFocusPainted(false);
        exitButton.setBounds(100, 280, 100, 30);
        exitButton.addActionListener(this);
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

