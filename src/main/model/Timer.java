package model;

public class Timer {

    private int minutes;
    private int seconds;

    // Constructor
    // EFFECTS: time has been set to 0:00:00
    public Timer(int m) {
        minutes = (m - 1);
        seconds = 60;
    }

    public void update() {

        while (minutes >= 0) {
            seconds--;
            System.out.println(seconds);
            if (seconds <= 0) {
                seconds = 60;
                minutes--;
            }
        }
        System.out.println("Time's up!");

    }

}
