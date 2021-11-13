package ui;

import model.TripTimer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//https://stackoverflow.com/questions/2281937/swing-jtextfield-how-to-remove-the-border
public class NewTripMenu extends JPanel implements ActionListener {
    private int minutes;
    Color notGray = new Color(54, 54, 54);
    Color lightGray = new Color(150, 150, 150);
    JButton moonButton;
    JButton marsButton;
    JButton saturnButton;
    JButton jupiterButton;
    JButton quitButton;
    JButton startButton;
    JTextField time;
    GUI gui;
    Font font;
    Image img = Toolkit.getDefaultToolkit().createImage("src/main/ui/images/Background.gif");
    List<JButton> buttons;

    public NewTripMenu(GUI gui) {
        menuSetUp(gui);
    }

    public void menuSetUp(GUI gui) {
        this.gui = gui;
        JLabel label = new JLabel("Choose Your Destination:");
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/ui/fonts/Nagoda.ttf"));
        } catch (FontFormatException e) {
            System.out.println("Font not accepted");
        } catch (IOException e) {
            System.out.println("IOException Caught");
        }
        label.setForeground(Color.white);
        label.setFont(font.deriveFont(15f));
        label.setBounds(60, 160, 250, 100);
        add(label);
        setLayout(null);
        buttonsSetUp();
        for (JButton next : buttons) {
            add(next);
        }
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setPreferredSize(new Dimension(300, 539));
    }

    public void textSetUp() {
        time = new JTextField("15");
        time.setBounds(60, 200, 180, 80);
        time.setFont(font.deriveFont(40f));
        time.setOpaque(false);
        time.setForeground(Color.white);
        time.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        time.setHorizontalAlignment(JTextField.CENTER);
    }

    @SuppressWarnings("methodlength")
    public void buttonsSetUp() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/ui/fonts/Nagoda.ttf"));
        } catch (FontFormatException e) {
            System.out.println("Font not accepted");
        } catch (IOException e) {
            System.out.println("IOException Caught");
        }
        quitButton = new JButton("Return");
        startButton = new JButton("Begin Journey");
        startButton.setBackground(notGray);
        startButton.setBorderPainted(false);
        startButton.setForeground(lightGray);
        startButton.setFont(font.deriveFont(15f));
        startButton.setFocusPainted(false);
        startButton.setBounds(60, 300, 180, 40);
        startButton.addActionListener(this);
        quitButton.setBackground(notGray);
        quitButton.setBorderPainted(false);
        quitButton.setForeground(lightGray);
        quitButton.setFont(font.deriveFont(15f));
        quitButton.setFocusPainted(false);
        quitButton.setBounds(20, 490, 260, 40);
        quitButton.addActionListener(this);
        moonButton = new JButton("The Moon - Requires 15 mins");
        marsButton = new JButton("Mars - Requires 30 mins");
        jupiterButton = new JButton("Jupiter - Requires 45 mins");
        saturnButton = new JButton("Saturn - Requires 60 mins");
        buttons = new ArrayList<>();
        buttons.add(saturnButton);
        buttons.add(jupiterButton);
        buttons.add(marsButton);
        buttons.add(moonButton);
        int y = 380;
        for (JButton next : buttons) {
            next.setBackground(notGray);
            next.setBorderPainted(false);
            next.setForeground(lightGray);
            next.setFont(font.deriveFont(15f));
            next.setFocusPainted(false);
            next.setBounds(20, y, 260, 40);
            next.addActionListener(this);
            y -= 50;
        }
    }

    public void tripToMoon() {
        textSetUp();
        add(time);
        add(startButton);
    }

    public void tripToMars() {
        textSetUp();
        time.setText("30");
        add(time);
        add(startButton);

    }

    public void tripToJupiter() {
        textSetUp();
        time.setText("45");
        add(time);
        add(startButton);
    }

    public void tripToSaturn() {
        textSetUp();
        time.setText("60");
        add(time);
        add(startButton);
    }

    public void startTrip() {
        minutes = Integer.parseInt(time.getText());
        TripTimer timer = new TripTimer(minutes);
        removeAll();
        try {
            timer.update();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        add(quitButton);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == quitButton) {
            remove(quitButton);
            menuSetUp(gui);
            gui.switchBackToMain();
        }
        if (e.getSource() == startButton) {
            startTrip();
        } else {
            removeAll();
            if (e.getSource() == moonButton) {
                tripToMoon();
            }
            if (e.getSource() == marsButton) {
                tripToMars();
            }
            if (e.getSource() == jupiterButton) {
                tripToJupiter();
            }
            if (e.getSource() == saturnButton) {
                tripToSaturn();
            }
        }
//        if (e.getSource() == moonButton) {
//            removeAll();
//            tripToMoon();
//        }
//        if (e.getSource() == marsButton) {
//            removeAll();
//            tripToMars();
//        }
//        if (e.getSource() == jupiterButton) {
//            removeAll();
//            tripToJupiter();
//        }
//        if (e.getSource() == saturnButton) {
//            removeAll();
//            tripToSaturn();
//        }
//        if (e.getSource() == quitButton) {
//            remove(quitButton);
//            menuSetUp(gui);
//            gui.switchBackToMain();
//        }
//        if (e.getSource() == startButton) {
//            startTrip();
//        }
    }

}
