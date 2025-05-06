package edu.najah.cs.special_cook_pms.manager;

import edu.najah.cs.special_cook_pms.model.Chef;
import java.util.*;

public class KitchenManager {

    private final List<Chef> chefs = new ArrayList<>();

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
}
