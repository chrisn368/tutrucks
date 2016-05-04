/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.controller;

import edu.temple.tutrucks.HibernateUtil;
import edu.temple.tutrucks.Tag;
import edu.temple.tutrucks.Truck;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;

/**
 *
 * @author michn_000
 */
@WebServlet(name = "AddTruckServlet", urlPatterns = {"/AddTruckServlet"})
public class AddTruckServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @return
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String truckName = request.getParameter("name");
        double lat = Double.parseDouble(request.getParameter("lat"));
        double lng = Double.parseDouble(request.getParameter("lng"));
        Time openTime = Time.valueOf(request.getParameter("open"));
        Time closeTime = Time.valueOf(request.getParameter("close"));
        String tagString = request.getParameter("tags");
        List<String> tags = Arrays.asList(tagString.split("\\s*,\\s*"));
        //check these value things
        Truck newTruck = new Truck();
        newTruck.setTruckName(truckName);
        newTruck.setLongitude(lng);
        newTruck.setLatitude(lat);
        newTruck.setOpeningTime(openTime);
        newTruck.setClosingTime(closeTime);
        System.out.println("about to save truck " + truckName);
        newTruck.save();
        //newTruck.loadTags();
        for (String s : tags) {
            Tag temp = Tag.retrieveTag(s, true);
            //temp = temp.loadTaggedEntities();
            temp.addEntity(newTruck);
            newTruck.addTags(temp);
            temp.save();
        }
        System.out.println("tryna print");
        try (PrintWriter out = response.getWriter()) {
            out.print("Truck added");
        }
        System.out.println("should be done");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
