package Main_facade.Order_class;

import java.time.LocalTime;
import java.util.ArrayList;

public class Order
{
    private boolean recived;
    private int nr;
    private String what_ordered;
    private int hour;
    private int min;
    private int hour_to_wait;
    private int min_to_wait;

    public Order()
    {
        this.recived = false;
        this.what_ordered = "";
        this.hour = LocalTime.now().getHour();
        this.min = LocalTime.now().getMinute();
    }

    public Order(String w_ordered)
    {
        this.recived = false;
        this.what_ordered = w_ordered;
        this.hour = LocalTime.now().getHour();
        this.min = LocalTime.now().getMinute();
    }

 // getters and seteres of class
    public String getWhat_ordered() {
        return what_ordered;
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }

    public int getHour_to_wait() {
        return hour_to_wait;
    }

    public int getMin_to_wait() {
        return min_to_wait;
    }

    public int getNr() {
        return nr;
    }

    public boolean isRecived() {
        return recived;
    }

    public void setWhat_ordered(String what_ordered) {
        this.what_ordered = what_ordered;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setHour_to_wait(int hour_to_wait) {
        this.hour_to_wait = hour_to_wait;
    }

    public void setMin_to_wait(int min_to_wait) {
        this.min_to_wait = min_to_wait;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public void setRecived(boolean recived) {
        this.recived = recived;
    }
}
