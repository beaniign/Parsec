package ui;

import model.Trip;
import model.TripLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class LogPanel extends JPanel implements ActionListener {
    private GUI gui;
    private Font font;
    private Image img = Toolkit.getDefaultToolkit().createImage("src/main/ui/images/Background.gif");
    private Color darkGray = new Color(54, 54, 54);
    private Color lightGray = new Color(150, 150, 150);
    private JButton exitButton;

    public LogPanel(GUI gui, TripLog tripLog) {
        this.gui = gui;

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/ui/fonts/Nagoda.ttf"));
        } catch (FontFormatException e) {
            System.out.println("Font not accepted");
        } catch (IOException e) {
            System.out.println("IOException Caught");
        }
        List<Trip> trips = tripLog.convertTripLogToList();
        int y = 40;
        for (Trip next : trips) {
            int i = trips.indexOf(next) + 1;
            JLabel label =
                    new JLabel("Trip #" + i + " - " + next.getLocation().toUpperCase()
                            + " - " + next.getDuration() + "mins - Note: " + next.getNote());
            label.setForeground(Color.white);
            label.setFont(font.deriveFont(12f));
            label.setBounds(20, y, 280, 15);
            add(label);
            y += 25;
        }
//        JLabel label = new JLabel();
//        label.setText("Saved to your Log Book!");
//        label.setForeground(Color.white);
//        label.setFont(font.deriveFont(20f));
//        label.setBounds(30, 200, 280, 100);
        buttonsSetUp();
        add(exitButton);
        setLayout(null);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setPreferredSize(new Dimension(300, 539));
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
        exitButton.setBackground(darkGray);
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

