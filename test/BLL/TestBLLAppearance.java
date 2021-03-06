package BLL;

import java.sql.Timestamp;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Team Kawabunga
 */
public class TestBLLAppearance {

    public TestBLLAppearance() {
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

    @Test
    /**
     * total tid er reelt 2 timer
     */
    public void testCalculateTotalTime() {
        System.out.println("totestCalculateTotalTime");
        Timestamp ci = new Timestamp(1399992800000L);//Tue May 13 2014 16:53:20 GMT+0200 (Rom, sommertid)
        Timestamp co = new Timestamp(1400000000000L);//Tue May 13 2014 18:53:20 GMT+0200 (Rom, sommertid)
        BLLAppearance instance = BLLAppearance.getInstance();
        int expResult = 2;
        int result = instance.calculateTotalTime(ci, co);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    @Test
    /**
     * total tid er reelt 5 timer expResult er 5
     */
    public void testCalculateTotalTime5hours() {
        System.out.println("totestCalculateTotalTime");
        Timestamp ci = new Timestamp(1399982000000L);//Tue May 13 2014 13:53:20 GMT+0200 (Rom, sommertid)
        Timestamp co = new Timestamp(1400000000000L);//Tue May 13 2014 18:53:20 GMT+0200 (Rom, sommertid)
        BLLAppearance instance = BLLAppearance.getInstance();
        int expResult = 5;
        int result = instance.calculateTotalTime(ci, co);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    @Test
    /**
     * total tid er reelt 5 timer 2min
     */
    public void testCalculateTotalTime6hours() {
        System.out.println("totestCalculateTotalTime");
        Timestamp ci = new Timestamp(1399981880000L);//May 13 2014 13:51:20 GMT+0200 (Rom, sommertid)
        Timestamp co = new Timestamp(1400000000000L);//Tue May 13 2014 18:53:20 GMT+0200 (Rom, sommertid)
        BLLAppearance instance = BLLAppearance.getInstance();
        int expResult = 6;
        int result = instance.calculateTotalTime(ci, co);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    @Test
    /**
     * total tid er reelt 4 timer 58min
     */
    public void testCalculateTotalTime5hoursv2() {
        System.out.println("totestCalculateTotalTime");
        Timestamp ci = new Timestamp(1399982120000L); //May 13 2014 13:55:20 GMT+0200 (Rom, sommertid)
        Timestamp co = new Timestamp(1400000000000L);//Tue May 13 2014 18:53:20 GMT+0200 (Rom, sommertid)
        BLLAppearance instance = BLLAppearance.getInstance();
        int expResult = 5;
        int result = instance.calculateTotalTime(ci, co);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    @Test
    /**
     * total tid er reelt 1 år
     */
    public void testCalculateTotalTime2hoursv2() {
        System.out.println("testTotestCalculateTotalTime2hoursv2");
        Timestamp ci = new Timestamp(1400000000000L); //Tue May 13 2014 18:53:20 GMT+0200 (Rom, sommertid)
        Timestamp co = new Timestamp(1431536000000L);//Wed May 13 2015 18:53:20 GMT+0200 (Rom, sommertid)
        BLLAppearance instance = BLLAppearance.getInstance();
        int expResult = 8760;
        int result = instance.calculateTotalTime(ci, co);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
     @Test
    /**
     * total tid er reelt 1 milisekund
     */
    public void testCalculateTotalTime1milisecond() {
        System.out.println("testTotestCalculateTotalTime2hoursv2");
        Timestamp ci = new Timestamp(1400000000000L); //Tue May 13 2014 18:53:20 GMT+0200 (Rom, sommertid)
        Timestamp co = new Timestamp(1400000000001L);//Tue May 13 2015 18:53:21 GMT+0200 (Rom, sommertid)
        BLLAppearance instance = BLLAppearance.getInstance();
        int expResult = 2;
        int result = instance.calculateTotalTime(ci, co);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
}
