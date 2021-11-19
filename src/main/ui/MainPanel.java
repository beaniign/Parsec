package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainPanel extends JPanel implements ActionListener {
    private static final Image IMG = Toolkit.getDefaultToolkit().createImage(
            "src/main/ui/images/Main_Background.gif");
    private static GUI GUI;

    private JButton newButton;
    private JButton checkButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton levelButton;
    private JButton exitButton;

    private Font font;
    private List<JButton> buttons;

    public MainPanel(GUI gui) {
        GUI = gui;
        setLayout(null);
        buttonsSetUp();
        for (JButton next : buttons) {
            add(next);
        }
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setPreferredSize(new Dimension(300, 539));
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
        newButton = new JButton("New Trip");
        checkButton = new JButton("Trip Logs");
        levelButton = new JButton("Colony Levels");
        saveButton = new JButton("Save Changes");
        loadButton = new JButton("Load Trips");
        exitButton = new JButton("Quit");
        buttons = new ArrayList<>();
        buttons.add(exitButton);
        buttons.add(loadButton);
        buttons.add(saveButton);
        buttons.add(levelButton);
        buttons.add(checkButton);
        buttons.add(newButton);
        int y = 385;
        for (JButton next : buttons) {
            next.setBorderPainted(false);
            next.setForeground(Color.white);
            next.setFont(font.deriveFont(22f));
            next.setFocusPainted(false);
            next.setOpaque(false);
            next.setHorizontalAlignment(SwingConstants.LEFT);
            next.setContentAreaFilled(false);
            next.setBounds(20, y, 260, 40);
            next.addActionListener(this);
            y -= 30;
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        g.drawImage(IMG, 0, 0, this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newButton) {
            GUI.switchToNewTripPanel();
        }
        if (e.getSource() == checkButton) {
            GUI.switchToLogPanel();
        }
        if (e.getSource() == levelButton) {
            GUI.switchToLevelPanel();
        }
        if (e.getSource() == saveButton) {
            GUI.switchToSavePanel();
        }
        if (e.getSource() == loadButton) {
            GUI.switchToLoadPanel();
        }
        if (e.getSource() == exitButton) {
            GUI.switchToQuitPanel();
        }
    }
}
