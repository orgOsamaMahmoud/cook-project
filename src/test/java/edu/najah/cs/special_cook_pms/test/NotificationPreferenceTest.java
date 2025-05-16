package edu.najah.cs.special_cook_pms.test;

import edu.najah.cs.special_cook_pms.model.NotificationPreference;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NotificationPreferenceTest {

    @Test
    public void testConstructorAndGetters() {
        NotificationPreference pref = new NotificationPreference("user1", "CHEF", "EMAIL", true, 6);
        assertEquals("user1", pref.getUserId());
        assertEquals("CHEF", pref.getUserType());
        assertEquals("EMAIL", pref.getChannel());
        assertTrue(pref.isEnabled());
        assertEquals(6, pref.getTimingHours());
    }

    @Test
    public void testSetters() {
        NotificationPreference pref = new NotificationPreference("x", "x", "x", false, 0);
        pref.setUserId("Ali");
        pref.setUserType("CUSTOMER");
        pref.setChannel("APP");
        pref.setEnabled(true);
        pref.setTimingHours(12);

        assertEquals("Ali", pref.getUserId());
        assertEquals("CUSTOMER", pref.getUserType());
        assertEquals("APP", pref.getChannel());
        assertTrue(pref.isEnabled());
        assertEquals(12, pref.getTimingHours());
    }
}
