package edu.najah.cs.special_cook_pms.test;

import edu.najah.cs.special_cook_pms.manager.InvoiceManager;
import edu.najah.cs.special_cook_pms.model.Customer;
import edu.najah.cs.special_cook_pms.model.Invoice;
import edu.najah.cs.special_cook_pms.model.Order;
import edu.najah.cs.special_cook_pms.model.OrderItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InvoiceManagerTest {

    @Test
    public void testGenerateInvoice() {
        InvoiceManager invoiceManager = new InvoiceManager();

        OrderItem item = new OrderItem("1", "Pizza", 2, 10.0);

        Order order = new Order("order123", "cust001");
        order.addItem(item); 

        Invoice invoice = invoiceManager.generateInvoice("cust001", "order123", order);

        assertNotNull(invoice);
        assertEquals("cust001", invoice.getCustomerId());
        assertEquals("order123", invoice.getOrderId());
        assertEquals(20.0, invoice.getTotalAmount(), 0.001);
        assertEquals(1, invoice.getItems().size());
    }
    
    @Test
    public void testUpdateInvoice_Success() {
        InvoiceManager invoiceManager = new InvoiceManager();

        Order originalOrder = new Order("order1", "cust001");
        originalOrder.addItem(new OrderItem("1", "Burger", 1, 8.0));
        Invoice invoice = invoiceManager.generateInvoice("cust001", "order1", originalOrder);
        String invoiceId = invoice.getInvoiceId();

        Order modifiedOrder = new Order("order1", "cust001");
        modifiedOrder.addItem(new OrderItem("2", "Juice", 2, 5.0)); 

        Invoice updatedInvoice = invoiceManager.updateInvoice(invoiceId, modifiedOrder);

        assertNotNull(updatedInvoice);
        assertEquals(10.0, updatedInvoice.getTotalAmount(), 0.001);
        assertEquals("UPDATED", updatedInvoice.getStatus());
        assertEquals(1, updatedInvoice.getItems().size());
        assertEquals("Juice", updatedInvoice.getItems().get(0).getProductName());
    }
    
    @Test
    public void testUpdateInvoice_NotFound() {
        InvoiceManager invoiceManager = new InvoiceManager();

        Order modifiedOrder = new Order("fakeOrder", "custX");
        modifiedOrder.addItem(new OrderItem("99", "Unknown", 1, 1.0));

        Invoice result = invoiceManager.updateInvoice("invalid-invoice-id", modifiedOrder);
        assertNull(result);
    }
    
    @Test
    public void testSendInvoice_StatusSent() {
        InvoiceManager invoiceManager = new InvoiceManager();

        Order order = new Order("order1", "cust001");
        order.addItem(new OrderItem("1", "Pizza", 1, 10.0));
        Invoice invoice = invoiceManager.generateInvoice("cust001", "order1", order);

        Customer customer = new Customer("cust001");
        customer.setPreferredInvoiceFormat("PDF");

        boolean result = invoiceManager.sendInvoice(invoice, customer);

        assertTrue(result);
        assertEquals("PDF", invoice.getFormat());
        assertEquals("SENT", invoice.getStatus());
    }
    
    @Test
    public void testSendInvoice_StatusRemainsUpdated() {
        InvoiceManager invoiceManager = new InvoiceManager();

        Order order = new Order("order1", "cust002");
        order.addItem(new OrderItem("2", "Pasta", 2, 7.5));
        Invoice invoice = invoiceManager.generateInvoice("cust002", "order1", order);

        invoice.setStatus("UPDATED");

        Customer customer = new Customer("cust002");
        customer.setPreferredInvoiceFormat("HTML");

        boolean result = invoiceManager.sendInvoice(invoice, customer);

        assertTrue(result);
        assertEquals("HTML", invoice.getFormat());
        assertEquals("UPDATED", invoice.getStatus()); 
    }
    
    @Test
    public void testGetInvoice() {
        InvoiceManager invoiceManager = new InvoiceManager();

        Order order = new Order("orderX", "cust007");
        order.addItem(new OrderItem("7", "Soup", 1, 4.0));
        Invoice invoice = invoiceManager.generateInvoice("cust007", "orderX", order);

        Invoice retrieved = invoiceManager.getInvoice(invoice.getInvoiceId());
        assertNotNull(retrieved);
        assertEquals(invoice.getInvoiceId(), retrieved.getInvoiceId());
    }





}
