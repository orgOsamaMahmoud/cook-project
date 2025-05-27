package edu.najah.cs.special_cook_pms;

import edu.najah.cs.special_cook_pms.manager.ChefManager;
import edu.najah.cs.special_cook_pms.model.Chef;
import edu.najah.cs.special_cook_pms.model.CookingTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AppAndChefManagerTest {

    private ChefManager chefManager;

    @BeforeEach
    void setUp() {
        chefManager = new ChefManager();
    }

    @Test
    void testRegisterChef() {
        Chef chef = chefManager.registerChef("Gordon");
        assertNotNull(chef);
        assertEquals("Gordon", chef.getName());
    }

    @Test
    void testScheduleCookingTask() throws ParseException {
        chefManager.registerChef("Julia");
        CookingTask task = chefManager.scheduleCookingTask("Julia", "ORDER-001", "2023-05-20 10:00:00");
        assertNotNull(task);
        assertEquals("Julia", task.getChefId());
        assertEquals("ORDER-001", task.getOrderId());
    }

    @Test
    void testCreateUrgentTask() {
        chefManager.registerChef("Mona");
        CookingTask urgentTask = chefManager.createUrgentTask("ORDER-URGENT");
        assertNotNull(urgentTask);
        assertTrue(urgentTask.isUrgent());
        assertEquals("ORDER-URGENT", urgentTask.getOrderId());
    }

    @Test
    void testAddPreparationRequirement() throws ParseException {
        chefManager.registerChef("Sam");
        CookingTask task = chefManager.scheduleCookingTask("Sam", "ORDER-123", "2023-05-20 12:00:00");
        chefManager.addPreparationRequirement(task.getTaskId(), "Boil", "Boil water for 5 mins");
        Map<String, String> requirements = chefManager.getPreparationRequirements(task.getTaskId());
        assertEquals("Boil water for 5 mins", requirements.get("Boil"));
    }

    @Test
    void testGetTasksForDate() throws ParseException {
        chefManager.registerChef("Nina");
        CookingTask task = chefManager.scheduleCookingTask("Nina", "ORDER-789", "2023-05-20 13:00:00");
        Date date = task.getCookingTime();
        List<CookingTask> tasks = chefManager.getTasksForDate("Nina", date);
        assertEquals(1, tasks.size());
        assertEquals("ORDER-789", tasks.get(0).getOrderId());
    }
    
   

    @Test
    void testAddPreparationRequirementToInvalidTask() {
        chefManager.addPreparationRequirement("TASK-INVALID", "Cut", "Chop veggies");
        Map<String, String> requirements = chefManager.getPreparationRequirements("TASK-INVALID");
        assertTrue(requirements.isEmpty());
    }

    @Test
    void testCreateUrgentTaskWhenNoChefsAvailable() {
        CookingTask urgentTask = chefManager.createUrgentTask("ORDER-NONE");
        assertNull(urgentTask);
    }

}
