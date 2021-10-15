package model;

//import java.util.Timer;
//import java.util.TimerTask;

public class TripTimer {

    private int minutes;
    private int seconds;

    // Constructor
    // EFFECTS: constructs a timer with minutes m
    public TripTimer(int m) {
        minutes = (m - 1);
        seconds = 60;
    }

    public void update() throws InterruptedException {

        while (minutes >= 0) {
            seconds--;
            Thread.sleep(1000);
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

    // The code below runs but for some reason the timer would show up after the "we've arrived" message

    //    public void update() {
    //        TimerTask task = new TimerTask() {
    //            @Override
    //            public void run() {
    //                seconds--;
    //                if (minutes >= 0) {
    //                    if (seconds < 10) {
    //                        System.out.println(minutes + ":0" + seconds);
    //                    } else {
    //                        System.out.println(minutes + ":" + seconds);
    //                    }
    //                    if (seconds <= 0) {
    //                        seconds = 60;
    //                        minutes--;
    //                    }
    //                }
    //            }
    //        };
    //        timer.scheduleAtFixedRate(task,0,1000);
    //
    //    }

}
