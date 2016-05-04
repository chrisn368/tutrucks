/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.controller;

import com.google.gson.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import edu.temple.tutrucks.Truck;
import edu.temple.tutrucks.TruckReview;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Nick Dell'Osa
 * @version %PROJECT_VERSION%
 */
public class TruckReviewFetchServlet extends HttpServlet {
   
    /**
     * Processes a get request for this servlet. This servlet fetches a specified truck's reviews and writes them to output as JSON.
     * This servlet takes 3 parameters: criteria, the ID of the truck to retrieve reviews for, start, the number of the first review to write to output, and end, the number of the last review to write to output.
     * The output of this request is a JSON array which contains JSON objects. The objects have 4 attributes: text, a String containing the text of the review, stars, the average total score of the truck, date, a String containing the date this review was written, and user, an object containing information about the writer of this review.
     * The user object contains 3 parameters itself: name, the display name of this user, email, the email of this user, and avatar, a link to this user's avatar.
     * @param req the HttpServletRequest object for this servlet
     * @param resp the HttpServletResponse object for this servlet
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        
        try {
            int truckID = Integer.parseInt(req.getParameter("criteria"));
            int minReview = req.getParameter("start") == null ? 0 : Integer.parseInt(req.getParameter("start"));
            int maxReview = req.getParameter("end") == null ? 100 : Integer.parseInt(req.getParameter("end"));
            Truck t = Truck.getTruckByID(truckID, true, false);
            List<TruckReview> reviews1 = t.getTruckReviews();
            String s;
            if (minReview>=reviews1.size()){
                minReview=0;
                maxReview=-1;
            }
            if (maxReview>=reviews1.size()) maxReview=reviews1.size();
            List<TruckReview> reviews = reviews1.subList(minReview, maxReview);
            
            JsonArray array = new JsonArray();
            for (TruckReview rev : reviews) {
                if (rev != null) {
                    JsonObject revObj = new JsonObject();
                    revObj.addProperty("text", rev.getReviewText());
                    revObj.addProperty("stars", rev.getReviewStars());
                    revObj.addProperty("date", rev.getReviewDate().toString());
                    JsonObject userInfo = new JsonObject();
                    userInfo.addProperty("name", rev.getUser().getDisplayName());
                    userInfo.addProperty("email", rev.getUser().getUserEmail());
                    userInfo.addProperty("avatar", rev.getUser().getAvatar());
                    userInfo.addProperty("uid", rev.getUser().getId());
                    revObj.add("userinfo", userInfo);
                    array.add(revObj);
                }
            }
            
            Gson gson = new GsonBuilder().serializeNulls().create();
            s = gson.toJson(array);
            resp.getWriter().print(s);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
