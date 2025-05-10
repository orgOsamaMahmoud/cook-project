package edu.najah.cs.special_cook_pms.model;

import java.util.ArrayList;
import java.util.List;

public class Chef {
    private String name;
    private List<NotificationPreference> notificationPreferences;
    private boolean available;

    public Chef(String name) {
        this.name = name;
        this.notificationPreferences = new ArrayList<>();
        this.available = true;

        // Default notification preferences
        this.notificationPreferences.add(new NotificationPreference(name, "CHEF", "EMAIL", true, 12));
        this.notificationPreferences.add(new NotificationPreference(name, "CHEF", "SMS", true, 4));
        this.notificationPreferences.add(new NotificationPreference(name, "CHEF", "APP", true, 24));
    }

    public String getName() {
        return name;
    }

    public List<NotificationPreference> getNotificationPreferences() {
        return notificationPreferences;
    }

    public void setNotificationPreferences(List<NotificationPreference> notificationPreferences) {
        this.notificationPreferences = notificationPreferences;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void updateNotificationPreference(String channel, boolean enabled, int timingHours) {
        for (NotificationPreference pref : notificationPreferences) {
            if (pref.getChannel().equals(channel)) {
                pref.setEnabled(enabled);
                pref.setTimingHours(timingHours);
                return;
            }
        }

        // If preference doesn't exist, add it
        notificationPreferences.add(new NotificationPreference(name, "CHEF", channel, enabled, timingHours));
    }
}