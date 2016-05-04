<%@page import="java.util.List"%>
<%@page import="edu.temple.tutrucks.Item"%>
<%@page import="edu.temple.tutrucks.Truck"%>
<%@page import="edu.temple.tutrucks.Taggable"%>
<%@page import="java.util.Set"%>
<%@page import="edu.temple.tutrucks.Tag"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<style>
    body{
        text-shadow: none;
        color: white;
    }
</style>
<div class="container">
    <div class="container">
    <h1>Enter or select a tag to delete:</h1>
    <form id="selectTag">
        <div class="col-sm-4">
            <input type="text" class="form-control" name="tagNameText">
        </div>
        <div class="col-sm-4">
            <select class="form-control" name="tagNameSelect">
                <%        
                    List<Tag> list = Tag.searchTags("*");
                    for (Tag t : list) {
                        out.print("<option>" + t.getTagName() + "</option>");
                    }
                %>
            </select>
        </div>
        <div class="col-sm-4">
            <button class='btn btn-primary' name='submitted'>Select Tag</button>
        </div>
    </form>
    </div>
    <div class="container">
        <form id="deleteTag">
        <%
        if (request.getParameter("submitted") != null) {
            Tag deleteTag = Tag.retrieveTag(request.getParameter("tagNameText"), false);            
            if (deleteTag == null) {           
                out.println("<h3>That tag does not exist.</h3>");                     
            } else {
                Set<Truck> trucks = deleteTag.getTrucks();
                out.print("<div class='container'>"
                        +"<input type='hidden' name= 'deleteTag' value='" + deleteTag.getTagName() + "'>"
                        + "<h4>These trucks will have this tag removed</h4>"
                        + "<div class='row-fluid'>");
                for (Truck t : trucks) {
                    out.print("<div class='col-sm-2'>"
                            + "<h5>" + t.getTruckName() + "</h5>"
                            + "</div>");
                }

                out.println("</div>"
                        + "</div>");

                Set<Item> items = deleteTag.getItems();
                out.print("<div class='container'>"
                        + "<h4>These items will have this tag removed</h4>"
                        + "<div class='row-fluid'>");

                for (Item t : items) {
                    out.print("<div class='col-sm-3'>"
                            + "<h5>" + t.getItemName() + "</h5>"
                            + "</div>");
                }

                out.println("</div>"
                        + "</div>"
                        + "<div class='container'>"
                        + "<h4>Do you wish to continue?</h4>"
                        +"<button class='btn btn-danger' type='submit'>Delete Tag</button>"
                        + "</div>");
            }
        }
    %>
        </form>
    </div>
</div>
<%@ include file="footer.html"%>
<script>
    $("#deleteTag").submit(function (e) {
    e.preventDefault();
    var tagName = $(e.target).find('[name=deleteTag]').val();
    $.ajax({
            type: "POST",
            url: "/DeleteTagServlet",
            data: {name: tagName},
            async: false,
            success: function (data) {
                alert(data);
            },
            error: function(error) {
                alert("There was an error.");
                console.log(error);
            }
    });

});
</script>