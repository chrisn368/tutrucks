<%-- 
    Document   : addUserImageModal
    Created on : May 3, 2016, 8:10:24 PM
    Author     : nickdellosa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="edu.temple.tutrucks.*"%>
<%@page import="java.util.List"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<div class="col-md-8 col-md-push-2">
    <form action="uploadImage" method="POST" enctype="multipart/form-data">
        <div class="col-sm-4">
            <input type="file" name="image">
            <input type="hidden" name="type" value="user">
        </div>
        <div class="col-sm-4">
            <input type="submit" class="btn btn-primary">
        </div>       
    </form>
</div>
