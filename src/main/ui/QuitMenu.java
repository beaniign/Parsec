package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class QuitMenu extends JPanel implements ActionListener {
    GUI gui;
    Font font;
    Image img = Toolkit.getDefaultToolkit().createImage("src/main/ui/images/Background.gif");
    Color notGray = new Color(54, 54, 54);
    Color lightGray = new Color(150, 150, 150);
    JButton returnButton;
    JButton yesButton;
    JButton noButton;


    public QuitMenu(GUI gui) {
        this.gui = gui;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/ui/fonts/Nagoda.ttf"));
        } catch (FontFormatException e) {
            System.out.println("Font not accepted");
        } catch (IOException e) {
            System.out.println("IOException Caught");
        }
        JLabel label = new JLabel();
        label.setText("Your trips are not saved!");
        label.setForeground(Color.white);
        label.setFont(font.deriveFont(15f));
        label.setBounds(60, 200, 200, 100);
        yesButton = new JButton("Save");
        noButton = new JButton("Don't Save");
        returnButton = new JButton("Return");
        buttonsSetUp(yesButton);
        buttonsSetUp(returnButton);
        buttonsSetUp(noButton);
        yesButton.setBounds(90, 280, 120, 30);
        noButton.setBounds(90, 320, 120, 30);
        returnButton.setBounds(90, 360, 120, 30);
        add(label);
        setLayout(null);

        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setPreferredSize(new Dimension(300, 539));


    }

    public void buttonsSetUp(JButton button) {
        button.setBackground(notGray);
        button.setBorderPainted(false);
        button.setForeground(lightGray);
        button.setFont(font.deriveFont(15f));
        button.setFocusPainted(false);
        button.addActionListener(this);
        add(button);
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

