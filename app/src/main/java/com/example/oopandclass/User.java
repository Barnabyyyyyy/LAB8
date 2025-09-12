package com.example.oopandclass;

public abstract class User {
        private String titleOfTextNote, contentOfTextNote;


        public User(String name, String userId) {
                this.titleOfTextNote = name;
                this.contentOfTextNote = userId;
        }

        // Encapsulation
        public String getName() {
                return titleOfTextNote;
        }

        public void setName(String name) {
                this.titleOfTextNote = name;
        }

        public String getUserId() {
                return contentOfTextNote;
        }

        public void setUserId(String userId) {
                this.contentOfTextNote = userId;
        }

        // Abstraction
        public abstract void showInfo();
}

