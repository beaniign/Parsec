package ui;

import javax.swing.*;
import java.awt.*;

// https://www.tutorialspoint.com/how-to-set-the-location-of-a-button-anywhere-in-jframe
// https://stackoverflow.com/questions/6593322/why-does-the-jframe-setsize-method-not-set-the-size-correctly
// https://stackoverflow.com/questions/20462167/increasing-font-size-in-a-jbutton
// https://stackoverflow.com/questions/14627223/how-to-change-a-jbutton-color-on-mouse-pressed
// https://www.codejava.net/java-se/swing/jlabel-basic-tutorial-and-examples
// https://stackoverflow.com/questions/12998604/adding-fonts-to-swing-application-and-include-in-package
// https://www.behance.net/gallery/128359661/Nagoda-Free-Modern-Display-Font?tracking_source=search_projects_recommended%7Cfree%20fonts
public class GUI {
    JFrame frame;
    JPanel mainPanel;
    JPanel levelPanel;

    public GUI() {
        frame = new JFrame("Parsec App");
        mainPanel = new MainMenu(this);
        levelPanel = new CheckLevel(this);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/ui/images/Parsec Favicon.png"));
        frame.getContentPane().add(mainPanel);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
        new ParsecApp();
    }

    public void switchToNewPanel() {
        frame.getContentPane().remove(mainPanel);
        frame.getContentPane().add(levelPanel);
        frame.revalidate();
    }

    public void switchToCheckPanel() {
        frame.getContentPane().remove(mainPanel);
        frame.getContentPane().add(levelPanel);
        frame.revalidate();
    }

    public void switchToLevelPanel() {
        frame.getContentPane().remove(mainPanel);
        frame.getContentPane().add(levelPanel);
        frame.revalidate();
    }

    public void switchToSavePanel() {
        frame.getContentPane().remove(mainPanel);
        frame.getContentPane().add(levelPanel);
        frame.revalidate();
    }

    public void switchToLoadPanel() {
        frame.getContentPane().remove(mainPanel);
        frame.getContentPane().add(levelPanel);
        frame.revalidate();
    }

    public void switchToQuitPanel() {
        System.exit(0);
    }
}

