package model;

import model.exception.TimeTooLongException;
import model.exception.TimeTooShortException;

public class Trip {

    protected int duration;
    protected String location;
    protected String note;

    //constructor
    public Trip(int duration, String location, String note) {
        this.duration = duration;
        this.note = note;
        this.location = location;
    }

    public void setTimer(int t) {
//        if (t < 15) {
//            throw new TimeTooShortException();
//        }
//        if (t > 120) {
//            throw new TimeTooLongException();
//        }
        Timer timer = new Timer(t);
        timer.update();
    }



}
