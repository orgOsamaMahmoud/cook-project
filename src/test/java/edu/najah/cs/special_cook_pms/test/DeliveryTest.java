package edu.najah.cs.special_cook_pms.test;

import edu.najah.cs.special_cook_pms.model.Delivery;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryTest {

    @Test
    public void testDefaultConstructor() {
        Delivery delivery = new Delivery();
        assertNotNull(delivery.getDeliveryId());
        assertEquals("SCHEDULED", delivery.getStatus());
        assertFalse(delivery.isSpecialOrder());
    }

    @Test
    public void testConstructorWithOrderIdAndDate() {
        Date now = new Date();
        Delivery delivery = new Delivery("ORD001", now);
        assertEquals("ORD001", delivery.getOrderId());
        assertEquals(now, delivery.getScheduledTime());
    }

    @Test
    public void testConstructorWithCustomerIdOrderIdDate() {
        Date now = new Date();
        Delivery delivery = new Delivery("CUST001", "ORD002", now);
        assertEquals("CUST001", delivery.getCustomerId());
        assertEquals("ORD002", delivery.getOrderId());
        assertEquals(now, delivery.getScheduledTime());
    }

    @Test
    public void testConstructorWithDateString() throws Exception {
        String dateStr = "2025-05-16 15:00:00";
        Delivery delivery = new Delivery("CUST002", "ORD003", dateStr);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date expectedDate = formatter.parse(dateStr);

        assertEquals("CUST002", delivery.getCustomerId());
        assertEquals("ORD003", delivery.getOrderId());
        assertEquals(expectedDate, delivery.getScheduledTime());
    }

    @Test
    public void testSettersAndGetters() {
        Delivery delivery = new Delivery();
        delivery.setCustomerId("CUST123");
        delivery.setOrderId("ORD456");
        delivery.setStatus("COMPLETED");

        Date date = new Date();
        delivery.setScheduledTime(date);
        delivery.setDeliveryId("DEL999");
        delivery.setSpecialOrder(true);

        assertEquals("CUST123", delivery.getCustomerId());
        assertEquals("ORD456", delivery.getOrderId());
        assertEquals("COMPLETED", delivery.getStatus());
        assertEquals(date, delivery.getScheduledTime());
        assertEquals("DEL999", delivery.getDeliveryId());
        assertTrue(delivery.isSpecialOrder());
    }
}
