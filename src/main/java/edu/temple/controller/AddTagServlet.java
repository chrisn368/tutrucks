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
 * @author Nick Dell'Osa
 * @version %PROJECT_VERSION%
 */
public class AddTagServlet extends HttpServlet {

    /**
     * Processes this servlets post request. This servlet adds a tag to the specified {@link Taggable} entity.
     * This servlet takes 4 parameters: user, the user that added the tag, names, the name(s) of the tag(s) to be added, id, the id of the entity to add this tag to, and type, the type of the {@link Taggable} entity
     * @param req the HttpServletRequest object for this servlet
     * @param resp the HttpServletResponse object for this servlet
     */
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
            for (Tag t : tags) t.save();
            JsonArray respArray = new JsonArray();
            for (Tag t : entity.loadTags().getTags()) {
                respArray.add(t.getTagName());
            }
            Gson gson = new Gson();
            String s = gson.toJson(respArray);
            resp.getWriter().print(s);
        } catch(NumberFormatException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
