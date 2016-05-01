/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import edu.temple.tutrucks.DBUtils;
import edu.temple.tutrucks.Item;
import edu.temple.tutrucks.Searchable;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Nick Dell'Osa
 * @version %PROJECT_VERSION%
 */
public class AutoCompleteServlet extends HttpServlet {

    /**
     * Processes the get request for this servlet. This servlets retrieves searchable information with the specified criteria and outputs it in JSON format.
     * This servlet takes 3 paramaters: criteria, the search criteria, numResults, the number of results to output, and subscripts, a boolean value on whether or not to display the subscripts associated with each {@link Searchable}
     * @param req the HttpServletRequest object for this servlet
     * @param resp the HttpServletResponse object for this servlet
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String search = req.getParameter("criteria");
        int numResults = req.getParameter("numResults") == null ? -1 : Integer.parseInt(req.getParameter("numResults"));
        boolean subs = req.getParameter("subscripts") == null ? false : Boolean.parseBoolean(req.getParameter("subscripts"));
        List<Searchable> results = DBUtils.searchAll(search, null);
        JsonArray tbp = new JsonArray();
        if (numResults < 0) {
            numResults = results.size();
        }

        for (int i = 0; i < Math.min(numResults, results.size()); i++) {
            String sn = results.get(i).getSearchName();
            if (subs) {
                String html = "<span class='ac_subtext'>*</span>";
                sn += html.replace("*", results.get(i) instanceof Item
                        ? "at " + (((Item) results.get(i)).getMenu().getTruck().getTruckName())
                        : results.get(i).getClass().getSimpleName());
            }
            tbp.add(sn);
        }
        Gson gson = new Gson();
        String s = gson.toJson(tbp);
        resp.getWriter().print(s);
    }
}
