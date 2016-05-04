/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.controller;

import edu.temple.tutrucks.Item;
import edu.temple.tutrucks.ItemReview;
import edu.temple.tutrucks.Tag;
import edu.temple.tutrucks.User;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.when;

/**
 *
 * @author nickdellosa
 */
public class ItemInfoFetchServletTest extends ServletTest {
    
    private static User realUser;
    private static Tag itag;
    
    @BeforeClass
    public static void setUpClass() {
        realUser = User.createUser("iteminfofetchservlettest@test.com", "password", false, null, null, null);
        itag = Tag.retrieveTag("iteminfofetchtag", true);   
    }
    
    @AfterClass
    public static void tearDownClass() {
        realUser.delete();
        itag.delete();
    }
    
    @Test
    public void doGetTest() {
        Item realItem = Item.getItemByID(1);
        ItemReview ir = new ItemReview();
        ir.setReviewStars(5);
        ir.setReviewed(realItem);
        ir.setUser(realUser);
        ir.setReviewText("review text");
        ir.setReviewDate(new Date());
        ir.save();
        itag.addEntity(realItem);
        itag.save();
        when(request.getParameter("criteria")).thenReturn("1");
        ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
        try (PrintWriter writer = new PrintWriter(baos)) {
            when(response.getWriter()).thenReturn(writer);
            new ItemInfoFetchServlet().doGet(request, response);
            writer.flush();
            String s = new String(baos.toByteArray());
            assertTrue(s.contains("[{\"text\":\"review text\",\"stars\":5,\""));
            assertTrue(s.contains("\"user\":{\"name\":\"iteminfofetchservlettest\",\"email\":\"iteminfofetchservlettest@test.com\""));
            assertTrue(s.contains("\"iteminfofetchtag\""));
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            fail();
        }
    }
}
