package model;

// Represents the timer for a trip with minutes and seconds
public class TripTimer {

    private int minutes;
    private int seconds;

    // Constructor
    // EFFECTS: constructs a timer with minutes m
    public TripTimer(int m) {
        minutes = (m - 1);
        seconds = 60;
    }

    // EFFECTS: gets the minutes value from a timer
    public int getMinutes() {
        return minutes;
    }

    // EFFECTS: gets the seconds value from a timer
    public int getSeconds() {
        return seconds;
    }

    // MODIFIES: this
    // EFFECTS: takes the minutes and seconds of a timer and increment it in a decreasing fashion just like a real
    //          countdown timer would, stops when the minute value becomes less than 0 (since 0 minutes doesn't
    //          necessarily mean the timer is over)
    public void update() throws InterruptedException {

        while (minutes >= 0) {
            seconds--;
            Thread.sleep(1);
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
        minutes = 0;
        seconds = 0;
    }
}
