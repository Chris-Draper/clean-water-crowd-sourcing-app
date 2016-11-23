package model;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import java.util.Date;

/**
 * Test suite for the Administrator Object
 */
public class AdministratorTest {

    private Administrator admin1;

    /**
     * Creates Administrator object for testing
     * @throws Exception - thrown on method failure
     */
    @Before
    public void setUp() throws Exception {
        final int admin1ID = 102;
        admin1 = new Administrator("chris9809", admin1ID);
    }

    /**
     * Test the getUsername() method
     */
    @Test
    public void adminUsername() {
        String username = "chris9809";
        Assert.assertEquals(username, admin1.getUsername());
    }

    /**
     * Test the getUserType() method
     */
    @Test
    public void adminUserType() {
        UserType testUserType = UserType.Administrator;
        Assert.assertEquals(testUserType, admin1.getUserType());
    }

    /**
     * Test the getID() method
     */
    @Test
    public void adminId() {
        final int checkID = 102;
        Assert.assertEquals(checkID, admin1.getID());
    }

    /**
     * Test the getDate() method
     */
    @Test
    public void date() {
        final int admin2ID = 99;
        Administrator admin3 = new Administrator("test-date", admin2ID);
        Date date = new Date();
        Assert.assertEquals(date, admin3.getDate());
    }

    /**
     * Test the null case for the equals() method
     */
    @Test
    public void nullAdminEqual() {
        Administrator admin3 = null;
        boolean result = admin1.equals(admin3);
        Assert.assertEquals(result, false);
    }

    /**
     * Test the not equal case for the equals() method
     */
    @Test
    public void adminsNotEqual() {
        final int admin4ID = 99;
        Administrator admin4 = new Administrator("mary-kat100", admin4ID);
        boolean result = admin1.equals(admin4);
        Assert.assertEquals(result, false);
    }

    /**
     * Test the does equal case for the equals() method
     */
    @Test
    public void adminsEqual() {
        final int admin5ID = 102;
        Administrator admin5 = new Administrator("chris9809", admin5ID);
        boolean result = admin1.equals(admin5);
        Assert.assertEquals(result, true);
    }

}