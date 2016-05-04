package edu.temple.tutrucks;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author michn_000
 */

import java.util.Arrays;
import java.util.Date;
import org.junit.*;
import static org.junit.Assert.*;


public class UserTest {
    private ItemReview itemReview;
    private TruckReview truckReview;  
    private User user;
    private static User realUser;
    private static final String EMAIL = "usertest@test.com";
    
    @BeforeClass
    public static void setup() {
        realUser = User.createUser(EMAIL, "password", false, null, null, null);
    }
    
    @AfterClass
    public static void tearDown() {
        realUser.delete();
    }
    
    @Before
    public void setUpMock() {
        itemReview = new ItemReview();   
        truckReview =  new TruckReview();
        user = new User();
    }
    
    @Test
    public void testMockCreation(){
        assertNotNull(user);
        assertNotNull(truckReview);
        assertNotNull(itemReview);
    }
    
    @Test
    public void testCreateUser() {
        assertEquals(EMAIL, realUser.getUserEmail());
    }
    
    @Test
    public void testValidateUserFacebook() {
        String email = "testfacebook@test.com";
        String fbID = "5555";
        User facebookUser1 = User.createUser(email, "password", true, "Facebook User", null, fbID);
        User facebookUser2 = User.validateUserFacebook(email, fbID);
        assertEquals(facebookUser1, facebookUser2);
        facebookUser1.delete();
    }
    
    @Test
    public void testValidateUser() {
        User testUser = User.validateUser(EMAIL, "password");
        assertEquals(realUser, testUser);
    }
    
    @Test
    public void testValidateUserMultiple() {
        for (int i=0; i < 5; i++) {
            User testUser = User.validateUser(EMAIL, "password");
            assertEquals(realUser, testUser);
        }
    }
    
    @Test
    public void testChangePassword() {
        User testUser = User.createUser("testaccount2@test.com", "password", false, null, null, null);
        byte[] testUserPassword = testUser.getPassWord();
        byte[] testUserSalt = testUser.getSalt();
        testUser.changePassword("password2");
        assertFalse(Arrays.equals(testUserSalt, testUser.getSalt()));
        assertFalse(Arrays.equals(testUserPassword, testUser.getPassWord()));
        testUser.delete();
    }
    
    @Test
    public void testLoadReviews() {
        Truck truck = Truck.getTruckByID(1);
        TruckReview tr = new TruckReview();
        Item item = Item.getItemByID(1);
        ItemReview ir = new ItemReview();
        tr.setReviewText("test");
        tr.setReviewStars(5);
        tr.setReviewDate(new Date());
        tr.setUser(realUser);
        tr.setTruck(truck);
        ir.setReviewText("test");
        ir.setReviewStars(5);
        ir.setReviewDate(new Date());
        ir.setUser(realUser);
        ir.setItem(item);
        tr.save();
        ir.save();
        realUser.loadUserReviews();
        assertTrue(realUser.getTruckReviews().contains(tr));
        assertTrue(realUser.getItemReviews().contains(ir));
        User realUser2 = User.loadUserByID(realUser.getId(), true);
        assertTrue(realUser2.getTruckReviews().contains(tr));
        assertTrue(realUser2.getItemReviews().contains(ir));
    }
    
    @Test
    public void testCreateUserBad() {
        String badEmail = "badEmail";
        String goodEmail = "goodEmail@test.com";
        String shortPass = "p";
        String longPass = "this is a very long password, far too long, absurdly long, why would you ever make a password this long?";
        try {
            User.createUser(badEmail, "whatever",false, null, null, null);
            fail();
            return;
        } catch (IllegalArgumentException ex) {}
        try {
            User.createUser(goodEmail, shortPass, false, null, null, null);
            fail();
            return;
        } catch (IllegalArgumentException ex) {}
        try {
            User.createUser(goodEmail, longPass, false, null, null, null);
            fail();
            return;
        } catch (IllegalArgumentException ex) {}
        try {
            User.createUser(EMAIL, "whatever", false, null, null, null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }
}






