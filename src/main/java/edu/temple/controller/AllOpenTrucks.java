/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import edu.temple.tutrucks.HibernateUtil;
import edu.temple.tutrucks.Truck;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author michn_000
 */
public class AllOpenTrucks extends HttpServlet {

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
        //response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Date today = new java.util.Date();
        if (today.getDay() == 0 || today.getDay() == 7){
            //its the weekend everything is closed
        }
        Date currentTime = new Time(today.getTime()); 
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("from Truck t where t.openingTime <=:time and t.closingTime >:time");
        q.setParameter("time", currentTime);
        List<Truck> results = q.list();
        //List<Truck> results = Truck.getAllTrucks();
       JsonArray jsonArray = new JsonArray();
        for (Truck t: results) {
               JsonObject jsonObject = new JsonObject();
               jsonObject.addProperty("name", t.getTruckName());
               jsonObject.addProperty("lat", t.getLatitude());
               jsonObject.addProperty("lng", t.getLongitude());
               jsonObject.addProperty("id", t.getId()); 
               jsonArray.add(jsonObject);
        }
        try (PrintWriter out = response.getWriter()) {
           out.print(jsonArray);  
        }
    }
}
