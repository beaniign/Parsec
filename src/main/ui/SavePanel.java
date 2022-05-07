package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

// represents the panel to display the save option
public class SavePanel extends JPanel implements ActionListener {
    private GUI gui;
    private Font font;
    private Image img = Toolkit.getDefaultToolkit().createImage("src/main/ui/images/Background.gif");
    private Color darkGray = new Color(54, 54, 54);
    private Color lightGray = new Color(200, 200, 200);
    private JButton exitButton;

    // MODIFIES: this
    // EFFECTS: constructor for SavePanel, sets up visual components including panel size, button and label
    public SavePanel(GUI gui) {
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
        label.setFont(font.deriveFont(20f));
        label.setBounds(30, 200, 280, 100);
        buttonsSetUp();
        add(label);
        add(exitButton);
        setLayout(null);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setPreferredSize(new Dimension(300, 539));
    }

    // EFFECTS: constructs button and apply styling
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

    // MODIFIES: this
    // EFFECTS: sets the background of the panel
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, this);
    }

    // EFFECTS: perform actions when a button is pressed, in this case tells the gui to switch back to the main panel
    @Override
    public void actionPerformed(ActionEvent e) {
        gui.switchBackToMain();
    }

}

