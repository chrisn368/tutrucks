<%@page import="java.util.List"%>
<%@page import="edu.temple.tutrucks.Truck"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<div class="container">
    <form id="form">
        <fieldset class="form-group form-inline">
            <label>Select a Truck to Delete</label>
            <select class="form-control" name="truckName">
                <%
                    Truck selected = new Truck();
                    boolean submitted = false;
                    List<Truck> list = Truck.getAllTrucks();
                    for (Truck t : list) {
                        out.print("<option value='" + t.getId() +"'>" + t.getTruckName() + "</option>");
                    }
                %>
            </select>
            <button type="submit" class="btn btn-danger">Delete Truck</button>    
        </fieldset>
    </form>
</div>
<%@ include file="footer.html"%>
<script>
    $("#form").submit(function (e) {
    e.preventDefault();
    var truckName = $(e.target).find('[name=truckName]').val();
    $.ajax({
            type: "POST",
            url: "/DeleteTruckServlet",
            data: {truckId: truckName},
            async: false,
            success: function (data) {
                alert(data);
                location.reload();
            },
            error: function(error) {
                alert("There was an error.");
                console.log(error);
            }
    });

});
</script>
