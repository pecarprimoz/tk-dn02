/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author neonpc
 */
public class StudentiTest {
    
    public StudentiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getIme method, of class Studenti.
     */
    @Test
    public void testGetIme() {
        System.out.println("getIme");
        Studenti instance = new Studenti("B", "A", "1", 7.3);
        String expResult = "B";
        String result = instance.getIme();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIme method, of class Studenti.
     */
    @Test
    public void testSetIme() {
        System.out.println("setIme");
        String ime = "";
        Studenti instance = new Studenti("B", "A", "1", 7.3);
        instance.setIme("F");
        assertEquals("1 | A, F | 7.3", instance.toString());
    }

    /**
     * Test of getPriimek method, of class Studenti.
     */
    @Test
    public void testGetPriimek() {
        System.out.println("getPriimek");
        Studenti instance = new Studenti("B", "A", "1", 7.3);
        String expResult = "A";
        String result = instance.getPriimek();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPriimek method, of class Studenti.
     */
    @Test
    public void testSetPriimek() {
        System.out.println("setPriimek");
        String priimek = "";
        Studenti instance = new Studenti("B", "A", "1", 7.3);
        instance.setPriimek("C");
        
        assertEquals("1 | C, B | 7.3", instance.toString());
    }

    /**
     * Test of getID method, of class Studenti.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Studenti instance = new Studenti("B", "A", "1", 7.3);
        String expResult = "1";
        String result = instance.getID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setID method, of class Studenti.
     */
    @Test
    public void testSetID() {
        System.out.println("setID");
        String ID = "";
        Studenti instance = new Studenti("B", "A", "1", 7.3);
        instance.setID("2");
        assertEquals("2 | A, B | 7.3", instance.toString());
    }

    /**
     * Test of getAvgGrade method, of class Studenti.
     */
    @Test
    public void testGetAvgGrade() {
        System.out.println("getAvgGrade");
        Studenti instance = new Studenti("B", "A", "1", 7.3);
        double expResult = 7.3;
        double result = instance.getAvgGrade();
        assertEquals(expResult, result,0.0);
    }

    /**
     * Test of setAvgGrade method, of class Studenti.
     */
    @Test
    public void testSetAvgGrade() {
        System.out.println("setAvgGrade");
        float avgGrade = 5.0F;
        Studenti instance = new Studenti("B", "A", "1", 7.3);
        instance.setAvgGrade(avgGrade);
        assertEquals("1 | A, B | 5.0", instance.toString());
    }

    /**
     * Test of toString method, of class Studenti.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Studenti instance = (new Studenti("B", "A", "1", 7.3));
        String expResult = "1 | A, B | 7.3";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
