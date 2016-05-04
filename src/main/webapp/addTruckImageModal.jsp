<%-- 
    Document   : addTruckImage
    Created on : May 2, 2016, 12:10:47 PM
    Author     : Chris
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="edu.temple.tutrucks.*"%>
<%@page import="java.util.List"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>

<%    
    List<Truck> trucks = Truck.getAllTrucks();
%>
<div class="col-md-8 col-md-push-2">
    <form action="uploadImage" method="POST" enctype="multipart/form-data">
        <div class="col-sm-4">
            <input type="file" name="image">
            <input type="hidden" name="type" value="truck">
        </div>
        <div class="col-sm-4">
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
        </div>
        <div class="col-sm-4">
            <input type="submit" class="btn btn-primary">
        </div>       
    </form>
</div>
