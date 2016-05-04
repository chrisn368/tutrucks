/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.controller;

import edu.temple.tutrucks.Truck;
import edu.temple.tutrucks.TruckReview;
import edu.temple.tutrucks.User;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 *
 * @author nickdellosa
 */
public class TruckReviewFetchServletTest extends ServletTest {
    
    private static User realUser;
    
    @BeforeClass
    public static void setUpClass() {
        realUser = User.createUser("truckreviewfetchservlettest@test.com", "password", false, null, null, null);
    }
    
    @AfterClass
    public static void tearDownClass() {
        realUser.delete();
    }
    
    @Test
    public void testDoGet() {
        Truck realTruck = Truck.getTruckByID(1);
        TruckReview tr = new TruckReview();
        tr.setReviewStars(5);
        tr.setReviewed(realTruck);
        tr.setUser(realUser);
        tr.setReviewText("review text");
        tr.setReviewDate(new Date());
        tr.save();
        when(request.getParameter("criteria")).thenReturn("1");
        ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
        try (PrintWriter writer = new PrintWriter(baos)) {
            when(response.getWriter()).thenReturn(writer);
            new TruckReviewFetchServlet().doGet(request, response);
            writer.flush();
            String s = new String(baos.toByteArray());
            assertTrue(s.contains("\"text\":\"review text\""));
            assertTrue(s.contains("\"stars\":5"));
            assertTrue(s.contains("\"name\":\"truckreviewfetchservlettest\",\"email\":\"truckreviewfetchservlettest@test.com\""));
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            fail();
        }
    }
    
}
