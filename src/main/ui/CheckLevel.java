package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class CheckLevel extends JPanel implements ActionListener {
    GUI gui;
    Font font;
    Image img;

    public CheckLevel(GUI gui) {
        this.gui = gui;
        img = Toolkit.getDefaultToolkit().createImage("src/main/ui/images/Background.gif");
        JLabel label = new JLabel();
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/ui/fonts/Nagoda.ttf"));
        } catch (FontFormatException e) {
            System.out.println("Font not accepted");
        } catch (IOException e) {
            System.out.println("IOException Caught");
        }
        label.setText("what");
        label.setForeground(Color.white);
        label.setFont(font.deriveFont(15f));
        label.setBounds(30, 30, 100, 100);
        add(label);
        setLayout(null);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setPreferredSize(new Dimension(300, 539));
    }


    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }
}

