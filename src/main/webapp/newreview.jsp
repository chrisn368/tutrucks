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
<style>
    body{
        text-shadow: none;
        color: white;
    }

    .panel .panel-heading, .panel-body{
        background-color: #A41E35;
        border: solid 5px white;
    }

    .panel-body {
        border-top: none;
    }

    .top-buffer {
        margin-top: 10px;
    }

    .remove {
        background-color: #A41E35;
        color: #999;
        border: solid 3px #999;

    }
    .remove:hover {
        background-color: #999;
        color: #A41E35;
        border: solid 3px #999;
    }

    .addButton {
        background-color: #A41E35;
        color: white;
        border: solid 3px white;
    }

    .addButton:hover {
        background-color: white;
        color: #A41E35;
        border: solid 3px white;
    }
</style>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" href="starRatingSystem/css/star-rating.css" media="all" rel="stylesheet" type="text/css"/>
<script src="starRatingSystem/js/star-rating.js" type="text/javascript"></script>

<% 
    if (user == null) response.sendError(HttpServletResponse.SC_FORBIDDEN, "You must be logged in to access this page");
    Reviewable r = null;
    String type = request.getParameter("type");
    String name="N/A";
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
            name=Truck.getTruckByID(id).getTruckName();
            break;
        case "item":
            r = Item.getItemByID(id);
            name=Item.getItemByID(id).getItemName()+" at "+Item.getItemByID(id).getMenu().getTruck().getTruckName();
            break;
        default:
            // error handling
            break;
    }
    if (r != null) {
        
    }
%>

<div class="container">
    <h1>Review for <%=name %> </h1>
    <form action="addReview" method="POST">
        <input type="hidden" name ="type" value="<%=type%>">
        <input type="hidden" name ="id" value="<%=id%>">
        <fieldset class="form-group">
            <div class="col-sm-4">
                <label for="Rating">Rating</label>
            </div>
            <div class="col-sm-8" style="text-align: left">
                <input id="rating-input" name="rating" type="number"/>
            </div>
        </fieldset>
        <fieldset class="form-group">
            <div class="col-sm-4">
                <label for="review">Review</label>
            </div>
            <div class="col-sm-8" style="text-align: left">
                <textarea rows=6 cols=50 placeholder="Review text Here" class="form-control" name="text"></textarea>
            </div>

        </fieldset>
        
        
        <button type="submit" id="submit" class="btn btn-primary addButton">Submit</button>
    </form>
</div>

<script>
    jQuery(document).ready(function () {
        $('#rating-input').rating({
              min: 0,
              max: 10,
              step: 1,
              size: 'md',
              stars: 5,
              showClear: false,
              showCaption: false
           });



    });
</script>

<%@ include file="footer.html"%>
