package edu.najah.cs.special_cook_pms.test;

import edu.najah.cs.special_cook_pms.manager.DeliveryManager;
import edu.najah.cs.special_cook_pms.model.Delivery;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryManagerTest {

    private String getFormattedFutureTime(int hoursAhead) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date(System.currentTimeMillis() + hoursAhead * 3600000));
    }

    @Test
    public void testScheduleDeliverySuccess() {
        DeliveryManager manager = new DeliveryManager();
        String time = getFormattedFutureTime(1);
        Delivery delivery = manager.scheduleDelivery("cust1", "order1", time);
        assertNotNull(delivery);
        assertEquals("cust1", delivery.getCustomerId());
        assertEquals("order1", delivery.getOrderId());
        assertEquals("SCHEDULED", delivery.getStatus());
    }

    @Test
    public void testScheduleDeliveryInvalidDate() {
        DeliveryManager manager = new DeliveryManager();
        Delivery delivery = manager.scheduleDelivery("cust1", "order1", "invalid-date");
        assertNull(delivery);
    }

    @Test
    public void testGetDelivery() {
        DeliveryManager manager = new DeliveryManager();
        String time = getFormattedFutureTime(1);
        Delivery scheduled = manager.scheduleDelivery("cust2", "order2", time);
        assertNotNull(scheduled);
        Delivery fetched = manager.getDelivery(scheduled.getDeliveryId());
        assertEquals(scheduled.getDeliveryId(), fetched.getDeliveryId());
    }

    @Test
    public void testGetUpcomingDeliveries() {
        DeliveryManager manager = new DeliveryManager();
        String time = getFormattedFutureTime(1);
        Delivery scheduled = manager.scheduleDelivery("cust3", "order3", time);
        assertNotNull(scheduled);
        List<Delivery> upcoming = manager.getUpcomingDeliveries();
        assertTrue(upcoming.contains(scheduled));
    }

    @Test
    public void testGetUpcomingDeliveriesForCustomer() {
        DeliveryManager manager = new DeliveryManager();
        String time = getFormattedFutureTime(1);
        Delivery scheduled = manager.scheduleDelivery("cust4", "order4", time);
        assertNotNull(scheduled);
        List<Delivery> customerDeliveries = manager.getUpcomingDeliveriesForCustomer("cust4");
        assertEquals(1, customerDeliveries.size());
        assertEquals("cust4", customerDeliveries.get(0).getCustomerId());
    }

    @Test
    public void testMarkDeliveryAsSpecial() {
        DeliveryManager manager = new DeliveryManager();
        String time = getFormattedFutureTime(1);
        Delivery delivery = manager.scheduleDelivery("cust5", "order5", time);
        assertNotNull(delivery);
        manager.markDeliveryAsSpecial(delivery.getDeliveryId());
        Delivery updated = manager.getDelivery(delivery.getDeliveryId());
        assertTrue(updated.isSpecialOrder());
    }
}
