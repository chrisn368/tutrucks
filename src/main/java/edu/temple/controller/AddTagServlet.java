/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import edu.temple.tutrucks.Item;
import edu.temple.tutrucks.Tag;
import edu.temple.tutrucks.Taggable;
import edu.temple.tutrucks.Truck;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nickdellosa
 */
public class AddTagServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (req.getSession() == null || req.getSession().getAttribute("user") == null) {
                // error handling
                return;
            }
            String name = req.getParameter("names");
            if (name == null || name.isEmpty()) {
                //error handling
                return;
            }
            String[] tagNames = name.split(",(\\s*)");
            Tag[] tags = new Tag[tagNames.length];
            int entityID = Integer.parseInt(req.getParameter("id"));
            String taggableType = req.getParameter("type");
            Taggable entity;
            switch (taggableType) {
                case "truck":
                    entity = Truck.getTruckByID(entityID);
                    break;
                case "item":
                    entity = Item.getItemByID(entityID);
                    break;
                default:
                    // error handling
                    return;
            }
            for (int i=0; i < tagNames.length; i++) {
                tags[i] = Tag.retrieveTag(tagNames[i], true);
            }
            entity.addTags(tags);
            JsonArray respArray = new JsonArray();
            for (Tag t : entity.loadTags()) {
                respArray.add(t.getTagName());
            }
            Gson gson = new Gson();
            resp.getWriter().print(gson.toJson(respArray));
        } catch(NumberFormatException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
