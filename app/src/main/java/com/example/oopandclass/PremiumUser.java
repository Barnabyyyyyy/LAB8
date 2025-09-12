package com.example.oopandclass;

public class PremiumUser extends User {
    private boolean isPremium;

    public PremiumUser(String name, String userId, boolean isPremium) {
        super(name, userId);
        this.isPremium = isPremium;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    // Polymorphism
    @Override
    public void showInfo() {
        System.out.println("User: " + getName() + " | ID: " + getUserId() + " | Premium: " + isPremium);
    }
}
