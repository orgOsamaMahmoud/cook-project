package edu.najah.cs.special_cook_pms.test;

import edu.najah.cs.special_cook_pms.model.Administrator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdministratorTest {

    @Test
    public void testConstructorAndGetters() {
        Administrator admin = new Administrator("ADM001", "Omar", "Manager");

        assertEquals("ADM001", admin.getAdminId());
        assertEquals("Omar", admin.getName());
        assertEquals("Manager", admin.getRole());
    }

    @Test
    public void testSetters() {
        Administrator admin = new Administrator();
        admin.setAdminId("ADM002");
        admin.setName("Laila");
        admin.setRole("Supervisor");

        assertEquals("ADM002", admin.getAdminId());
        assertEquals("Laila", admin.getName());
        assertEquals("Supervisor", admin.getRole());
    }
}
