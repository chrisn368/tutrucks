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
public class EditUserInfoServlet extends HttpServlet {
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String displayName = req.getParameter("displayname");
        String old = req.getParameter("oldpassword");
        String password = req.getParameter("newpassword");
        HttpSession session = req.getSession();
        if (session == null) {
            resp.sendRedirect("error.jsp?msg=An error has occurred.");
            return;
        }
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect("error.jsp?msg=You are not logged in!");
            return;
        }
        if (displayName != null) {
            user.setDisplayName(displayName);
            resp.getWriter().print(user.getDisplayName());
        }
        if (old != null && password != null) {
            User user2 = User.validateUser(user.getUserEmail(), old);
            if (user.equals(user2)) {
                if (password.length() > 16) {
                    resp.getWriter().print("Password too long (16 characters max).");
                } else if (password.length() < 6) {
                    resp.getWriter().print("Password too short (6 characters min).");
                }
                user.changePassword(password);
                resp.getWriter().print("Password changed successfully.");
            } else {
                resp.getWriter().print("Old password does not match password entered in the database.");
            }
        }
        user.save();
    }
}
