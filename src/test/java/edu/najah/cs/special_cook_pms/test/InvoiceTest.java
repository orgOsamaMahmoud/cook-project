package edu.najah.cs.special_cook_pms.test;

import edu.najah.cs.special_cook_pms.model.Invoice;
import edu.najah.cs.special_cook_pms.model.InvoiceItem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InvoiceTest {

    @Test
    public void testInvoiceSettersAndGetters() {
        Invoice invoice = new Invoice();

        invoice.setInvoiceId("INV-999");
        invoice.setCustomerId("CUST-1");
        invoice.setOrderId("ORD-1");
        invoice.setTotalAmount(250.75);

        List<InvoiceItem> items = new ArrayList<>();
        invoice.setItems(items);

        invoice.setStatus("PAID");
        invoice.setFormat("PDF");

        Date created = new Date();
        Date modified = new Date(created.getTime() + 10000);
        invoice.setCreatedDate(created);
        invoice.setModifiedDate(modified);

        assertEquals("INV-999", invoice.getInvoiceId());
        assertEquals("CUST-1", invoice.getCustomerId());
        assertEquals("ORD-1", invoice.getOrderId());
        assertEquals(250.75, invoice.getTotalAmount());
        assertEquals(items, invoice.getItems());
        assertEquals("PAID", invoice.getStatus());
        assertEquals("PDF", invoice.getFormat());
        assertEquals(created, invoice.getCreatedDate());
        assertEquals(modified, invoice.getModifiedDate());
    }
}
