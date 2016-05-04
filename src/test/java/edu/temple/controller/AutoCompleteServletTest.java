/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import static org.mockito.Mockito.when;

/**
 *
 * @author nickdellosa
 */
public class AutoCompleteServletTest extends ServletTest {
    
    @Test
    public void testDoGet() {
        when(request.getParameter("criteria")).thenReturn("truck:chicken");
        when(request.getParameter("numResults")).thenReturn("1");
        when(request.getParameter("subscripts")).thenReturn("false");
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(128);
            try (PrintWriter writer = new PrintWriter(baos, true)) {
                when(response.getWriter()).thenReturn(writer);
                new AutoCompleteServlet().doGet(request, response);
                writer.flush();
                assertEquals("[\"Chicken Heaven\"]", new String(baos.toByteArray()));
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            fail();
        }
    }
    
    @Test
    public void testDoGetWithSubscripts() {
        when(request.getParameter("criteria")).thenReturn("truck:chicken");
        when(request.getParameter("numResults")).thenReturn("1");
        when(request.getParameter("subscripts")).thenReturn("true");
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(128);
            try (PrintWriter writer = new PrintWriter(baos, true)) {
                when(response.getWriter()).thenReturn(writer);
                new AutoCompleteServlet().doGet(request, response);
                writer.flush();
                assertEquals("[\"Chicken Heaven\\u003cspan class\\u003d\\u0027ac_subtext\\u0027\\u003eTruck\\u003c/span\\u003e\"]", new String(baos.toByteArray()));
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            fail();
        }
    }
}
