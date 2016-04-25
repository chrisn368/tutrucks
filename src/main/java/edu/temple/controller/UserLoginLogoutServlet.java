/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.controller;

import edu.temple.tutrucks.User;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nickdellosa
 */
public class UserLoginLogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(true);
        String redirect = req.getParameter("redirect");
        if (req.getServletPath().endsWith("login")) {
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String fbID;
            User u;
            if (password == null) {
                fbID = req.getParameter("fbID");
                u = User.validateUserFacebook(email, fbID);
                session.setAttribute("user", u);
            } else {
                u = User.validateUser(email, password);
                session.setAttribute("user", u);
            }
        } else {
            Object o = session.getAttribute("user");
            if (o != null && o instanceof User) {
                User u = (User) o;
                u.save();
                session.setAttribute("user", null);
            }
        }
        resp.sendRedirect(redirect==null?"/":redirect);
    }
}
