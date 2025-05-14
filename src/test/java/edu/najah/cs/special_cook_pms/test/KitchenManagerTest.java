package edu.najah.cs.special_cook_pms.test;

import edu.najah.cs.special_cook_pms.manager.KitchenManager;
import edu.najah.cs.special_cook_pms.model.Chef;
import edu.najah.cs.special_cook_pms.model.Notification;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class KitchenManagerTest {

    @Test
    void testDefaultConstructor() {
        KitchenManager manager = new KitchenManager();
        assertEquals("Default Kitchen", manager.getName());
    }

    @Test
    void testAddAndGetChefs() {
        KitchenManager manager = new KitchenManager("Main Kitchen");
        Chef chef = new Chef("Ali");
        manager.addChef(chef);

        List<Chef> chefs = manager.getChefs();
        assertEquals(1, chefs.size());
        assertEquals("Ali", chefs.get(0).getName());
    }

    @Test
    void testAssignTaskWithExpertChef() {
        KitchenManager manager = new KitchenManager("Task Kitchen");
        Chef chef = new Chef("Sara", "Low", "Dessert");
        chef.setAvailable(true);
        manager.addChef(chef);

        Chef assigned = manager.assignTask("Dessert");
        assertNotNull(assigned);
        assertEquals("Sara", assigned.getName());
        assertEquals("Medium", assigned.getWorkload());
    }

    @Test
    void testAssignTaskWithFallback() {
        KitchenManager manager = new KitchenManager();
        Chef chef = new Chef("Ahmed", "Low", "Grill");
        chef.setAvailable(true);
        manager.addChef(chef);

        Chef assigned = manager.assignTask("UnknownType");
        assertNotNull(assigned);
        assertEquals("Ahmed", assigned.getName());
        assertEquals("Medium", assigned.getWorkload());
    }

    @Test
    void testAssignTaskWithNoAvailableChef() {
        KitchenManager manager = new KitchenManager();
        Chef chef = new Chef("Tariq", "High", "Soup");
        chef.setAvailable(true);
        manager.addChef(chef);

        Chef assigned = manager.assignTask("Soup");
        assertNull(assigned);
    }

    @Test
    void testAddAndGetNotifications() {
        KitchenManager manager = new KitchenManager("Notif Kitchen");

        Notification notif = new Notification();
        notif.setContent("New Order");

        manager.addNotification(notif);

        List<Notification> list = manager.getNotifications();
        assertEquals(1, list.size());
        assertEquals("New Order", list.get(0).getContent());
    }
}
