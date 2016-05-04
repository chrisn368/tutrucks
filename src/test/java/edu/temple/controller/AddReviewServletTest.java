/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.controller;

import edu.temple.tutrucks.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 *
 * @author nickdellosa
 */ 

public class AddReviewServletTest extends ServletTest {
    
    private static User realUser;
    
    @BeforeClass
    public static void setup() {
        realUser = User.createUser("addreviewservlettest@test.com", "password", false, null, null, null);
    }
    
    @AfterClass
    public static void tearDown() {
        realUser.delete();
    }
    
    @Test
    public void testDoPostTruck() {
        when(session.getAttribute("user")).thenReturn(realUser);
        when(request.getParameter("text")).thenReturn("test review");
        when(request.getParameter("type")).thenReturn("truck");
        when(request.getParameter("rating")).thenReturn("5");
        when(request.getParameter("id")).thenReturn("1");
        new AddReviewServlet().doPost(request, response);
        User user = realUser.loadUserReviews();
        assertTrue(user.getTruckReviews().size() > 0);
    }
    
    @Test
    public void testDoPostItem() {
        when(session.getAttribute("user")).thenReturn(realUser);
        when(request.getParameter("text")).thenReturn("test review");
        when(request.getParameter("type")).thenReturn("item");
        when(request.getParameter("rating")).thenReturn("5");
        when(request.getParameter("id")).thenReturn("1");
        new AddReviewServlet().doPost(request, response);
        User user = realUser.loadUserReviews();
        assertTrue(user.getItemReviews().size() > 0);
    }
}
