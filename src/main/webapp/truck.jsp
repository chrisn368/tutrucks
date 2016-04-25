<%@ include file="header.jsp"%>
<%@ include file="truckReviewModal.jsp"%>
<%@ include file="itemReviewModal.jsp"%>
<%@ page import="edu.temple.tutrucks.*"%>
<%@ page import="java.util.List, java.util.Set,java.util.Locale,java.text.NumberFormat"%>

<style>
    .panel-heading {
        background-color: black;
    }
    .panel-title {
        color: #A41E35;
    }
    .panel-body {
        color: black;
    }

    .itemName {
        text-align: left;
        color: #A41E35;
    }

    .modal-header{
        background-color:  #A41E35;
        color: white;
    }

    .modal-body {
        color: black;
    }

    .click{
        cursor: pointer;
    }
</style>

<%
    String search = request.getParameter("truck");
    Truck truck = Truck.getTruckByID(Integer.parseInt(search));
    String truckName = truck.getTruckName();
    int truckID = truck.getId();
    List<Menu> menus = truck.getMenus();
    
%>

<div class="container menu">
    <div class="row">
        <div class="col-lg-8" style="text-align: left;">
            <h1 style="color: white;"><%=truckName%></h1>
            <p style="color: white"><%
                    Set<Tag> tags = truck.loadTags();
                    if (!(tags.isEmpty() && user == null)) {
                        out.print("Tags: <span><span id='current_tags'>");
                        if (!tags.isEmpty()) {
                            StringBuilder tagHTML = new StringBuilder();
                            for (Tag t : tags) {
                                tagHTML.append("<a class='taglinks' href='search.jsp?tagged="
                                        + t.getTagName() + "'>" + t.getTagName() + "</a>, ");
                            }
                            if (user == null) {
                                out.print(tagHTML.subSequence(0, tagHTML.lastIndexOf(",")));
                            } else {
                                out.print(String.valueOf(tagHTML));
                            }
                        }
                        if (user != null) {
                            out.print("</span><a id='tag_adder' href='#'>add tags...</a>"
                                + "<input type='text' id='tag_add_field' hidden />"
                                + "<input type='button' title='Enter new tags, separated by commas' id='tag_add_button' hidden /></span>");
                        }
                    }
                    %></p>
        </div>
        <div class="col-lg-4" style="text-align: right;">
            <h1 class ="click" style="color: white" data-toggle="modal" data-target="#truckModal" 
                data-truckid="<%=truckID%>">
                <%
                    truck.loadReviews();
                            int avgRating=truck.getScore();
                            int fullStars=avgRating/2;
                            int halfStars=avgRating%2;
                            out.print("Reviews: ");
                            if (avgRating==0){
                                out.print("None");
                            }
                            for (int i=0;i<fullStars;i++){
                                out.print("<img src='images/Star_Full.png' width='24' height='24'>");
                            }
                            if (halfStars==1){
                                out.print("<img src='images/Star_Half.png' width='12' height='24'>");
                            }
                    %>
                    </h1>
        </div>
    </div>

    <div class="row">
        <!--map business -->
    </div>

    <!--copied from category.jsp-->
    <%
        for (Menu category : menus) {
            if (category == null) {     //ignore null menu category from db
                continue;
            }
    %>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h1 class="panel-title"> 
                <%
                    String menuName = category.getMenuName();
                    if (menuName != null && menuName != "") {
                        out.print(menuName);
                    } else {
                        out.print("Menu");
                    }
                %>
            </h1>
            <h5 style="font-style: italic"> 
                <%
                    String description = category.getDescription();
                    if (description != null && description != "") {
                        out.print(description);
                    }
                %>
            </h5>
        </div>

        <div class="panel-body">
            <div class ="container">
                <%
                    Set<Item> items = category.getItems();
                    for (Item item : items) {
                        if (item == null) {
                            continue;
                        }
                        int itemID = item.getId();
                %>
                <!--copied from menuItem.jsp-->
                <div class="row-fluid">                                  
                    <div class="col-lg-8 itemName">
                        <%
                            String itemName = item.getItemName();
                            out.print(itemName);
                        %>
                    </div>
                    <div class="col-lg-2">
                        <%
                            double price = item.getPrice();
                            out.print(NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(price));
                        %>
                    </div>
                    <div class="col-lg-2 click" data-toggle="modal" data-target="#itemModal" data-truckid="<%=itemID%>">
                        <%
                            double stars = 0.0;
                            double averageStars = 0.0;
                            List<ItemReview> reviews = item.getItemReviews();
                        
                            if (reviews.size() > 0) {
                                stars = item.getScore();
                                averageStars = stars / 2;
                                out.print(averageStars);
                            }
                        %>
                    </div>                                   
                </div> 
                <!--end menuItem.jsp-->
                <% } %>
            </div>
        </div>
    </div>
    <!--end category.jsp-->
    <% }%>
</div>
    <script>
    $(document).ready(function() {
        $("#tag_adder").click(function () {
            $("#tag_add_button").val('Cancel');
            $("#tag_add_field").show();
            $("#tag_add_button").show();
        });
        $("#tag_add_field").keyup(function () {
            var changeTextToAdd = $(this).val().length > 0;
            $("#tag_add_button").val(changeTextToAdd ? 'Add Tag' : 'Cancel');            
        });
        $("#tag_add_button").click(function () {
            var addTag = $("#tag_add_field").val();
            if (addTag) {
                console.log("making ajax call for " + addTag);
                $.ajax("addtags", {
                    method: "POST",
                    dataType: "json",
                    data: { names: $("#tag_add_field").val(), id: <%=truckID%>, type: "truck" },
                    success: function (data) {
                        var result = "";
                        for (var i=0; i < data.length; i++) {
                            result += ("<a class='taglinks' href='search.jsp?tagged=" + data[i] + ">"
                                   + data[i] + "</a>, ");
                        }
                        $("#current_tags").html(result);
                    },
                    error: function(jqHXR, status, error) {
                    
                    }
                });
            } else {
                $("#tag_add_field").hide();
                $("#tag_add_button").hide();
            }
        });
    });
    </script>
<%@ include file="footer.html"%>

