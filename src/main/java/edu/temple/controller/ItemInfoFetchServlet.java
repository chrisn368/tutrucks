/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.controller;

import com.google.gson.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import edu.temple.tutrucks.Item;
import edu.temple.tutrucks.ItemReview;
import edu.temple.tutrucks.Tag;
import java.text.DateFormat;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Nick Dell'Osa
 * @version %PROJECT_VERSION%
 */
public class ItemInfoFetchServlet extends HttpServlet {
    
    /**
     * Processes a get request for this servlet. This servlet fetches a specified item's reviews and writes them to output as JSON.
     * This servlet takes 1 parameter: criteria, the ID of the item to retrieve reviews for.
     * The output of this request is a JSON object which contains 2 JSON arrays.
     * The first array, reviews, contains JSON objects. The objects have 4 attributes: text, a String containing the text of the review, stars, the average total score of the truck, date, a String containing the date this review was written, and user, an object containing information about the writer of this review.
     * The second array, tags, contains JSON strings which correspond to the respective names of the tags associated with this item.
     * The user object contains 3 parameters itself: name, the display name of this user, email, the email of this user, and avatar, a link to this user's avatar.
     * @param req the HttpServletRequest object for this servlet
     * @param resp the HttpServletResponse object for this servlet
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
           
           int itemID = Integer.parseInt(req.getParameter("criteria"));
           Item item = Item.getItemByID(itemID, true, true);
           List<ItemReview> reviews = item.getItemReviews();
           JsonArray jsArrayReviews = new JsonArray();
           for (ItemReview ir : reviews) {
               if (ir!=null){
               
               JsonObject cur = new JsonObject();
               cur.addProperty("text", ir.getReviewText());
               cur.addProperty("stars", ir.getReviewStars());
               
               cur.addProperty("date", DateFormat.getDateInstance().format(ir.getReviewDate()));
               JsonObject jsUser = new JsonObject();
               jsUser.addProperty("name", ir.getUser().getDisplayName());
               jsUser.addProperty("email", ir.getUser().getUserEmail());
               jsUser.addProperty("avatar", ir.getUser().getAvatar());
               jsUser.addProperty("uid", ir.getUser().getId());
               
               cur.add("user", jsUser);
               jsArrayReviews.add(cur);
               }
           }
           
           JsonObject retval = new JsonObject();
           retval.add("reviews", jsArrayReviews);
           Set<Tag> tags = item.getTags();
           JsonArray jsArrayTags = new JsonArray();
           for (Tag t : tags) {
               jsArrayTags.add(t.getTagName());
           }
           retval.add("tags", jsArrayTags);
           Gson gson = new GsonBuilder().serializeNulls().create();
           String s = gson.toJson(retval);
           resp.getWriter().print(s);
        } catch (Exception e) {
            
        }
    }
}
