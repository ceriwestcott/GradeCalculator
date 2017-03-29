package com.example.ceri.howmuchtoachieve;

/**
 * Created by Ceri on 31-Jan-17.
 */

public class Pair {
    private double mark;
    private double percentage;

    public Pair(double mark, double percentage){
        this.mark = mark;
        this.percentage = percentage;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
