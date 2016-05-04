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
 * @author Nick Dell'Osa
 * @version %PROJECT_VERSION%
 */
public class UserLoginLogoutServlet extends HttpServlet {

    /**
     * Processes the post request for this servlet. This servlet logs a user in or out, depending on the URL (/login for logins, /logout for logouts).
     * This servlet takes 4 parameters: email, the email of the user logging in, password, the unencrypted password of the user logging in, fbID, the Facebook ID of the user logging in if the user is logging in via Facebook, and redirect, the page the user is logging in from.
     * @param req the HttpServletRequest object for this servlet
     * @param resp the HttpServletResponse object for this servlet
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(true);
        String redirect = req.getParameter("redirect");
        if (req.getServletPath().endsWith("login")) {
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String displayName= req.getParameter("display");
            String avatarURL = req.getParameter("avatar");
            String fbID;
            User u;
            if (password == null) {
                fbID = req.getParameter("fbID");
                u = User.validateUserFacebook(email, fbID);
                session.setAttribute("user", u);
                u.linkUserFacebook(fbID, displayName, avatarURL);
            } else {
                u = User.validateUser(email, password);
                System.out.println("User: "+u);
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
        return;
    }
}
