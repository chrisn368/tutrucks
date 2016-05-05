/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import edu.temple.tutrucks.Item;
import edu.temple.tutrucks.ItemDeserializer;
import edu.temple.tutrucks.Menu;
import edu.temple.tutrucks.MenuDeserializer;

/**
 *
 * @author michn_000
 */

public class EditMenuServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String menuString = request.getParameter("menu");
        String truckName = request.getParameter("truckName");
        
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Menu.class, new MenuDeserializer());
        gsonBuilder.registerTypeAdapter(Item.class, new ItemDeserializer());
        Gson gson = gsonBuilder.create();
        
        Menu[] menuArray = gson.fromJson(menuString, Menu[].class);
        
        try (PrintWriter out = response.getWriter()) {
            out.print(menuArray[2].toString());
        }
    }
}
