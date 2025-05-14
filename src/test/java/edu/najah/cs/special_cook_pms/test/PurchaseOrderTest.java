package edu.najah.cs.special_cook_pms.test;

import edu.najah.cs.special_cook_pms.manager.PurchaseOrder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PurchaseOrderTest {

    @Test
    void testConstructorAndGetters() {
        PurchaseOrder order = new PurchaseOrder("Rice", 5);

        assertEquals("Rice", order.getItem());
        assertEquals(5, order.getQuantity());
    }
}
