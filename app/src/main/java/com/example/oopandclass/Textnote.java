package com.example.oopandclass;

import java.util.Date;

public class Textnote extends Note {
    private String textContent;

    public Textnote(String title, Date createdDate, String textContent) {
        super(title, createdDate);
        this.textContent = textContent;
    }

    public Textnote(String title, Date createdDate) {
        super(title, createdDate);
    }

    // Encapsulation
    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    // Polymorphism
    @Override
    public String display() {
        return "TextNote: " + getTitle() + ": " + textContent + " (" + getCreatedDate() + ")";
    }
}

