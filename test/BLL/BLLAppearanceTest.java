///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package BLL;
//
//import BE.BEAlarm;
//import BE.BEAppearance;
//import java.util.ArrayList;
//import java.util.Date;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author AndreThy
// */
//public class BLLAppearanceTest {
//    
//    public BLLAppearanceTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    
//    @Test
//    public void testGetAppearancesWithCriteria() {
//        System.out.println("getAppearancesWithCriteria");
//        Date date = null;
//        String time = "10:30";
//        int tolerance = 10;
//        BLLAppearance instance = BLLAppearance.getInstance();
//        ArrayList<BEAppearance> expResult = null;
//        ArrayList<BEAppearance> result = instance.getAppearancesWithCriteria(date, time, tolerance);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//}
