<%-- 
    Document   : error
    Created on : May 3, 2016, 2:06:11 PM
    Author     : nickdellosa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="header.jsp"%>
<% 
    String errMsg = request.getParameter("msg");
%>
<h2 style="color:white;"><%= errMsg%></h2>
<%@include file="footer.html" %>
