package edu.najah.cs.special_cook_pms.test;

import edu.najah.cs.special_cook_pms.model.OrderItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderItemTest {

    @Test
    public void testConstructorAndGetters() {
        OrderItem item = new OrderItem("P123", "Tomato", 5, 2.5);

        assertEquals("P123", item.getProductId());
        assertEquals("Tomato", item.getProductName());
        assertEquals(5, item.getQuantity());
        assertEquals(2.5, item.getUnitPrice(), 0.001);
    }

    @Test
    public void testSetters() {
        OrderItem item = new OrderItem();

        item.setProductId("P999");
        item.setProductName("Onion");
        item.setQuantity(10);
        item.setUnitPrice(3.75);

        assertEquals("P999", item.getProductId());
        assertEquals("Onion", item.getProductName());
        assertEquals(10, item.getQuantity());
        assertEquals(3.75, item.getUnitPrice(), 0.001);
    }
}
