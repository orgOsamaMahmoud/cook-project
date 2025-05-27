package edu.najah.cs.special_cook_pms;

import edu.najah.cs.special_cook_pms.manager.ChefManager;
import edu.najah.cs.special_cook_pms.model.Chef;
import edu.najah.cs.special_cook_pms.model.CookingTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AppAndChefManagerTest {

    private ChefManager chefManager;

    @BeforeEach
    void setUp() {
        chefManager = new ChefManager();
    }

    @Test
    void testRegisterChefDefault() {
        Chef chef = chefManager.registerChef("Gordon");
        assertNotNull(chef);
        assertEquals("Gordon", chef.getName());
    }

    @Test
    void testRegisterChefWithExpertise() {
        Chef chef = chefManager.registerChef("Julia", "Pastry", "Medium");
        assertNotNull(chef);
        assertEquals("Julia", chef.getName());
        assertEquals("Pastry", chef.getExpertise());
        assertEquals("Medium", chef.getWorkload());
    }

    @Test
    void testScheduleCookingTaskValid() throws ParseException {
        chefManager.registerChef("Nina");
        CookingTask task = chefManager.scheduleCookingTask("Nina", "ORDER-1", "2023-05-20 09:00:00");
        assertNotNull(task);
        assertEquals("ORDER-1", task.getOrderId());
    }

    @Test
    void testScheduleCookingTaskChefNotFound() throws ParseException {
        CookingTask task = chefManager.scheduleCookingTask("Ghost", "ORDER-404", "2023-05-20 09:00:00");
        assertNull(task);
    }

    @Test
    void testAddPreparationRequirementValid() throws ParseException {
        chefManager.registerChef("Sara");
        CookingTask task = chefManager.scheduleCookingTask("Sara", "ORDER-2", "2023-05-20 10:00:00");
        chefManager.addPreparationRequirement(task.getTaskId(), "Chop", "Chop veggies finely");
        Map<String, String> reqs = chefManager.getPreparationRequirements(task.getTaskId());
        assertEquals("Chop veggies finely", reqs.get("Chop"));
    }

    @Test
    void testAddPreparationRequirementTaskNotFound() {
        chefManager.addPreparationRequirement("INVALID-TASK", "Clean", "Clean area");
        Map<String, String> reqs = chefManager.getPreparationRequirements("INVALID-TASK");
        assertTrue(reqs.isEmpty());
    }

    @Test
    void testCreateUrgentTaskWithAvailableChef() {
        chefManager.registerChef("Ali", "Grill", "Low");
        CookingTask urgentTask = chefManager.createUrgentTask("URGENT-ORDER");
        assertNotNull(urgentTask);
        assertEquals("URGENT-ORDER", urgentTask.getOrderId());
        assertTrue(urgentTask.isUrgent());
    }

    @Test
    void testCreateUrgentTaskNoChefAvailable() {
        CookingTask urgentTask = chefManager.createUrgentTask("NO-CHEF");
        assertNull(urgentTask);
    }

    @Test
    void testGetTasksForDateValid() throws ParseException {
        chefManager.registerChef("Lina");
        CookingTask task = chefManager.scheduleCookingTask("Lina", "ORDER-5", "2023-05-21 13:00:00");
        Date date = task.getCookingTime();
        List<CookingTask> tasks = chefManager.getTasksForDate("Lina", date);
        assertEquals(1, tasks.size());
    }

    @Test
    void testGetChefWhenExists() {
        chefManager.registerChef("Yousef");
        Chef chef = chefManager.getChef("Yousef");
        assertNotNull(chef);
    }

    @Test
    void testGetChefWhenNotExists() {
        Chef chef = chefManager.getChef("NotThere");
        assertNull(chef);
    }

    @Test
    void testGetPreparationRequirementsWhenNone() {
        Map<String, String> requirements = chefManager.getPreparationRequirements("unknown");
        assertNotNull(requirements);
        assertTrue(requirements.isEmpty());
    }
}
