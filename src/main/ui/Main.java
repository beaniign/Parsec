package ui;

import model.Timer;

public class Main {
    public static void main(String[] args) {
        Timer timer = new Timer(2);
        timer.update();
    }
}
