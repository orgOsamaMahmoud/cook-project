package edu.najah.cs.special_cook_pms.test;

import edu.najah.cs.special_cook_pms.manager.CookingTaskManager;
import edu.najah.cs.special_cook_pms.model.Chef;
import edu.najah.cs.special_cook_pms.model.CookingTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CookingTaskManagerTest {

    private CookingTaskManager manager;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @BeforeEach
    public void setUp() {
        manager = new CookingTaskManager();
    }

    @Test
    public void testRegisterAndGetChef() {
        Chef chef = manager.registerChef("Omar");
        assertNotNull(chef);
        assertEquals("Omar", chef.getName());

        Chef fetched = manager.getChef("Omar");
        assertEquals(chef, fetched);
    }

    @Test
    public void testScheduleCookingTaskSuccess() throws Exception {
        manager.registerChef("Ayman");
        String time = "2025-05-15 12:18:56";
        CookingTask task = manager.scheduleCookingTask("Ayman", "ORD001", time);

        assertNotNull(task);
        assertEquals("Ayman", task.getChefId());
        assertEquals("ORD001", task.getOrderId());
    }

    @Test
    public void testScheduleCookingTaskInvalidDate() {
        CookingTask task = manager.scheduleCookingTask("Ali", "ORD002", "invalid date");
        assertNull(task);
    }

    @Test
    public void testGetCookingTask() throws Exception {
        manager.registerChef("Lina");
        CookingTask task = manager.scheduleCookingTask("Lina", "ORD003", "2025-05-15 12:18:56");

        CookingTask fetched = manager.getCookingTask(task.getTaskId());
        assertNotNull(fetched);
        assertEquals("ORD003", fetched.getOrderId());
    }

    @Test
    public void testGetTasksForChef() throws Exception 
    {
        manager.registerChef("Zaid");

        manager.scheduleCookingTask("Zaid", "ORD001", "2025-05-15 10:00:00");

        Thread.sleep(10); // توقف بسيط عشان يتغير taskId
        manager.scheduleCookingTask("Zaid", "ORD002", "2025-05-16 12:00:00");

        List<CookingTask> tasks = manager.getTasksForChef("Zaid");
        assertEquals(2, tasks.size());
    }



    @Test
    public void testGetTasksForDate() throws Exception 
    {
        manager.registerChef("Salma");

        manager.scheduleCookingTask("Salma", "ORD100", "2025-05-15 12:18:56");
        manager.scheduleCookingTask("Salma", "ORD101", "2025-05-16 12:18:56");

        Date targetDate = new SimpleDateFormat("yyyy-MM-dd").parse("2025-05-15");

        List<CookingTask> tasks = manager.getTasksForDate("Salma", targetDate);
        assertEquals(1, tasks.size());
        assertEquals("ORD100", tasks.get(0).getOrderId());
    }


    @Test
    public void testCreateUrgentTask() {
        Chef chef = manager.registerChef("Sara");
        chef.setAvailable(true);

        CookingTask task = manager.createUrgentTask("ORD999");
        assertNotNull(task);
        assertTrue(task.isUrgent());
        assertEquals("Sara", task.getChefId());
    }

    @Test
    public void testCreateUrgentTaskNoAvailableChef() 
    {
        CookingTaskManager manager = new CookingTaskManager();
        Chef chef = manager.registerChef("BusyChef");
        chef.increaseWorkload(); 
        chef.increaseWorkload(); 
        CookingTask task = manager.createUrgentTask("order001");
        assertNull(task); 
    }



    @Test
    public void testAddPreparationRequirement() throws Exception {
        manager.registerChef("Hassan");
        CookingTask task = manager.scheduleCookingTask("Hassan", "ORD555", "2025-05-15 12:18:56");
        manager.addPreparationRequirement(task.getTaskId(), "Chicken", "Marinate overnight");

        assertTrue(task.getPreparationRequirements().containsKey("Chicken"));
        assertEquals("Marinate overnight", task.getPreparationRequirements().get("Chicken"));
    }
}
