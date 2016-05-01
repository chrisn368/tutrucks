<%-- 
    Document   : newreview
    Created on : May 1, 2016, 3:44:10 PM
    Author     : nickdellosa
--%>

<%@page import="edu.temple.tutrucks.Item"%>
<%@page import="edu.temple.tutrucks.Reviewable"%>
<%@page import="edu.temple.tutrucks.Truck"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<% 
    if (user == null) response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this page");
    Reviewable r = null;
    String type = request.getParameter("type");
    int id;
    try {
        id = Integer.parseInt(request.getParameter("id"));
    } catch (NumberFormatException ex) {
        // error handling
        return;
    }
    switch (type) {
        case "truck":
            r = Truck.getTruckByID(id);
            break;
        case "item":
            r = Item.getItemByID(id);
            break;
        default:
            // error handling
            break;
    }
    if (r != null) {
        
    }
%>
<form action="addReview" method="POST">
    
</form>
<%@ include file="footer.html"%>
