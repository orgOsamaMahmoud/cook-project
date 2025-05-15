package edu.najah.cs.special_cook_pms.manager;

import edu.najah.cs.special_cook_pms.model.Chef;
import edu.najah.cs.special_cook_pms.model.Notification;

import java.util.*;

public class KitchenManager {

    private String name; // تم إزالة final
    private final List<Chef> chefs = new ArrayList<>();
    private final List<Notification> notifications = new ArrayList<>();

    public KitchenManager(String name) {
        this.name = name;
    }

    public KitchenManager() {
        this.name = "Default Kitchen";
    }

    public void addChef(Chef chef) {
        chefs.add(chef);
    }

    public Chef assignTask(String taskType) {
        List<Chef> available = chefs.stream()
                .filter(Chef::isAvailable)
                .toList();

        for (Chef chef : available) {
            if (chef.getExpertise().equalsIgnoreCase(taskType)) {
                chef.increaseWorkload();
                return chef;
            }
        }

        if (!available.isEmpty()) {
            Chef fallback = available.get(0);
            fallback.increaseWorkload();
            return fallback;
        }

        return null;
    }

    public List<Chef> getChefs() {
        return chefs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addNotification(Notification notification) {
        notifications.add(notification);
    }

    public List<Notification> getNotifications() {
        return notifications;
    }
}
