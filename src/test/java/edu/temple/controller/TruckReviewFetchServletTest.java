/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.controller;

import edu.temple.tutrucks.TruckReview;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author nickdellosa
 */
public class TruckReviewFetchServletTest {
    
    public TruckReviewFetchServletTest() {
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
    /*
    @Test
    public void testDoGet() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("criteria")).thenReturn("9");
        when(request.getParameter("start")).thenReturn("0");
        when(request.getParameter("end")).thenReturn("1");
        ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
        try (PrintWriter writer = new PrintWriter(baos, true)) {
            when(response.getWriter()).thenReturn(writer);
            new TruckReviewFetchServlet().doGet(request, response);
            writer.flush();
            assertEquals("[{\"text\":\"the most popular truck on campus, one of the best too, though a bit expensive\",\"stars\":8,\"date\":\"2016-04-10\",\"userinfo\":{\"name\":\"nick.dellosa\",\"email\":\"nick.dellosa@yahoo.com\"}}]", new String(baos.toByteArray()));
        } catch (IOException e) {
            fail();
        }
    }
*/
}
