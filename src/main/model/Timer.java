package model;

public class Timer {

    private int minutes;
    private int seconds;

    // Constructor
    // EFFECTS: time has been set to m
    public Timer(int m) {
        minutes = (m - 1);
        seconds = 60;
    }

    public void update() {

        while (minutes >= 0) {
            seconds--;
            if (seconds < 10) {
                System.out.println(minutes + ":0" + seconds);
            } else {
                System.out.println(minutes + ":" + seconds);
            }
            if (seconds <= 0) {
                seconds = 60;
                minutes--;
            }
        }

    }

}
