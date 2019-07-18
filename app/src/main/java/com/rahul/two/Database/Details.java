package com.rahul.two.Database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Details {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "from")
    private String from;
    @ColumnInfo(name = "to")
    private String to;
    @ColumnInfo(name = "distance")
    private String distance;

    public Details(String from, String to, String distance) {

        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
