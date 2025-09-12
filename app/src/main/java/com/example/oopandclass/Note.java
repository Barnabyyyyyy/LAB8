package com.example.oopandclass;

import java.util.Date;

public class Note {
    protected String title;
    protected Date createdDate;

    public Note(String title, Date createdDate) {
        this.title = title;
        this.createdDate = createdDate;
    }

    // Encapsulation
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    // Polymorphism
    public String display() {
        return "Note: " + title + " (" + createdDate + ")";
    }
}