package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class QuitPanel extends JPanel implements ActionListener {
    private GUI gui;
    private Font font;
    private Image img = Toolkit.getDefaultToolkit().createImage("src/main/ui/images/Background.gif");
    private Color darkGray = new Color(54, 54, 54);
    private Color lightGray = new Color(150, 150, 150);
    private JButton returnButton;
    private JButton yesButton;
    private JButton noButton;


    public QuitPanel(GUI gui) {
        this.gui = gui;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/ui/fonts/Nagoda.ttf"));
        } catch (FontFormatException e) {
            System.out.println("Font not accepted");
        } catch (IOException e) {
            System.out.println("IOException Caught");
        }
        yesButton = new JButton("Save");
        noButton = new JButton("Don't Save");
        returnButton = new JButton("Return");
        List<JButton> buttons = new ArrayList<>();
        buttons.add(yesButton);
        buttons.add(noButton);
        buttons.add(returnButton);
        buttonsSetUp(buttons);
        labelSetUp();
        setLayout(null);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setPreferredSize(new Dimension(300, 539));
    }

    public void labelSetUp() {
        JLabel label = new JLabel();
        label.setText("Your trips are not saved!");
        label.setForeground(Color.white);
        label.setFont(font.deriveFont(15f));
        label.setBounds(60, 190, 200, 100);
        add(label);
    }

    public void buttonsSetUp(List<JButton> buttons) {
        int y = 270;
        for (JButton next : buttons) {
            next.setBounds(90, y, 120, 30);
            next.setBackground(darkGray);
            next.setBorderPainted(false);
            next.setForeground(lightGray);
            next.setFont(font.deriveFont(15f));
            next.setFocusPainted(false);
            next.addActionListener(this);
            add(next);
            y += 40;
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnButton) {
            gui.switchBackToMain();
        }
        if (e.getSource() == noButton) {
            System.exit(0);
        }
        if (e.getSource() == yesButton) {
            gui.saveTripLog();
            System.exit(0);
        }
    }

}

