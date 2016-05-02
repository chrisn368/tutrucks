<%-- 
    Document   : addTruckImage
    Created on : May 2, 2016, 12:10:47 PM
    Author     : Chris
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="edu.temple.tutrucks.*"%>
<%@page import="java.util.List"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>

<%    
    List<Truck> trucks = Truck.searchTrucks("*");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <form action="uploadImage" method="POST">
            <input type="file" name="image">
            <input type="hidden" name="truck">
            <select name="trucks">
                <%
                    String truckName;
                    int id;
                    for (Truck t : trucks) {
                        truckName = t.getTruckName();
                        id = t.getId();
                        out.print("<option value=" + id + ">" + truckName + "</option>");
                    }
                %>
            </select>
            <input type="submit">
        </form>
    </body>
</html>
