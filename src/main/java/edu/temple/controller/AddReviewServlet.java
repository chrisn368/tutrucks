/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.controller;

import edu.temple.tutrucks.Item;
import edu.temple.tutrucks.ItemReview;
import edu.temple.tutrucks.Review;
import edu.temple.tutrucks.Reviewable;
import edu.temple.tutrucks.Truck;
import edu.temple.tutrucks.TruckReview;
import edu.temple.tutrucks.User;
import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nickdellosa
 */
public class AddReviewServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User)req.getSession().getAttribute("user");
        String text = req.getParameter("text");
        String type = req.getParameter("type");
        int rating, id;
        try {
            rating = Integer.parseInt(req.getParameter("rating"));
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException ex) {
            // error handling
            return;
        }
        Date date = new Date();
        Review review = null;
        Reviewable r = null;
        int redirectID = 0;
        switch (type) {
            case "truck":
                r = Truck.getTruckByID(id);
                review = new TruckReview();
                redirectID = id;
                break;
            case "item":
                Item item = Item.getItemByID(id);
                redirectID = item.getMenu().getTruck().getId();
                r = item;
                review = new ItemReview();
                break;
            default:
                // error handling
                break;
        }
        if (r != null) {
            review.setReviewDate(date);
            review.setReviewStars(rating);
            review.setReviewText(text);
            review.setUser(user);
            review.setReviewed(r);
            r.addReview(review);
            review.save();
            try {
                resp.sendRedirect("truck.jsp?id=" + redirectID);
            } catch (IOException ex) {
                
            }
        }
    }
    
}
