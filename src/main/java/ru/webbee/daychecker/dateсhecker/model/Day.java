package ru.webbee.daychecker.dateсhecker.model;

public class Day {
    private boolean isWeekend;
    private int number;
    private String month;
    public Day(boolean isWeekend, int number, String month) {
        this.isWeekend = isWeekend;
        this.number = number;
        this.month = month;
    }
    @Override
    public String toString() {
        return "Day{" +
                "isWeekend=" + isWeekend +
                ", number=" + number +
                ", month='" + month + '\'' +
                '}';
    }

}
