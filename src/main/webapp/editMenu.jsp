<%@page import="edu.temple.tutrucks.Tag"%>
<%@page import="edu.temple.tutrucks.User"%>
<%@page import="java.util.Set"%>
<%@page import="edu.temple.tutrucks.Item"%>
<%@page import="edu.temple.tutrucks.Menu"%>
<%@page import="java.util.List"%>
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

    .btn-primary, .btn-primary:active, .btn-primary:focus {
        background-color: #A41E35;
        color: white;
        border: solid 3px white;
    }

    .btn-primary:hover {
        background-color: white;
        color: #A41E35;
        border: solid 3px white;
    }

    .otherButton, .otherButton:active, .otherButton:focus{
        color: white;
        background-color: #A41E35;
        border-color: white;
        border: 3px solid white;
    }
    .otherButton:hover {
        color: #A41E35;
        background-color: white;
        border: 3px solid white;
    }
</style>
<div class="cover container">
    <form id="selected">
        <fieldset class="form-group form-inline">
            <label>Select a Truck to Edit</label>
            <select class="form-control" name="truckName">
                <%  
                    Truck selected = new Truck();
                    boolean submitted = false;
                    if (request.getParameter("submitted") != null) {
                        selected = Truck.getTruckByID(Integer.parseInt(request.getParameter("truckName")));
                        submitted = true;
                    }
                    List<Truck> list = Truck.getAllTrucks();
                    for (Truck t : list) {
                        if (t.getId() == selected.getId()) {
                            out.print("<option value='" + t.getId() + "'selected>" + t.getTruckName() + "</option>");
                        } else {
                            out.print("<option value='" + t.getId() + "'>" + t.getTruckName() + "</option>");
                        }
                    }
                %>
            </select>
            <button class="btn btn-primary otherButton" name="submitted" value="">Submit</button>    
        </fieldset>
    </form>
    <form id="form">
        <input type="hidden" name="truckName" value=<%out.print("'" + selected.getTruckName() + "'");%>>
        <fieldset class="form-group formWrapper">
            <div class="container containerPanelWrapper">
                <div class="panel panel-default panelCloneWrapper hidden">
                    <div class="panel-heading">
                        <input type="hidden" name="menuId" value="0">
                        <input type="text" class="form-control" name="categoryTitle" placeholder="Category Title">
                        <input type="text" class="form-control" name="categoryDescription" placeholder="Category Description">
                        <button type="button" class="form-control btn btn-danger" id="removeCategory">Remove</button> 
                    </div>
                    <div class="panel-body outerWrapper">
                        <div class ="container-fluid containerWrapper">
                            <div class="row-fluid">
                                <div class="col-sm-3">
                                    <h4>Name</h4>
                                </div>
                                <div class="col-sm-2">
                                    <h4>Price</h4>
                                </div>
                                <div class="col-sm-5">
                                    <h4>Tags</h4>
                                </div>
                                <div class="col-sm-2">
                                    <h4>&nbsp;</h4>
                                </div>
                            </div>
                            <div class="row-fluid cloneWrapper">
                                <input type="hidden" name="itemId" value="0">
                                <div class="col-sm-3" id="itemName">
                                    <input type="text" class="form-control" name="itemName" placeholder="Item Name">
                                </div>
                                <div class="col-sm-2" id="itemPrice">
                                    <input type="text" class="form-control" name="itemPrice" placeholder="Item Price">       
                                </div> 
                                <div class="col-sm-5" id="tags">
                                    <textarea class="form-control" style="min-width: 100%; margin-bottom: 5px;" rows="1" name="tags"></textarea>
                                </div> 
                                <div class="col-sm-2">
                                    <button type="button" class="form-control btn btn-danger removeItem">Remove</button>       
                                </div>                              
                            </div>
                            <div class="row-fluid cloneWrapper">
                                <input type="hidden" name="itemId" value="0">
                                <div class="col-sm-3" id="itemName">
                                    <input type="text" class="form-control" name="itemName" placeholder="Item Name">
                                </div>
                                <div class="col-sm-2" id="itemPrice">
                                    <input type="text" class="form-control" name="itemPrice" placeholder="Item Price">       
                                </div> 
                                <div class="col-sm-5" id="tags">
                                    <textarea class="form-control" style="min-width: 100%; margin-bottom: 5px;" rows="1" name="tags"></textarea>
                                </div> 
                                <div class="col-sm-2">
                                    <button type="button" class="form-control btn btn-danger removeItem">Remove</button>       
                                </div>                              
                            </div>
                            <div class="row-fluid cloneWrapper">
                                <input type="hidden" name="itemId" value="0">
                                <div class="col-sm-3" id="itemName">
                                    <input type="text" class="form-control" name="itemName" placeholder="Item Name">
                                </div>
                                <div class="col-sm-2" id="itemPrice">
                                    <input type="text" class="form-control" name="itemPrice" placeholder="Item Price">       
                                </div> 
                                <div class="col-sm-5" id="tags">
                                    <textarea class="form-control" style="min-width: 100%; margin-bottom: 5px;" rows="1" name="tags"></textarea>
                                </div> 
                                <div class="col-sm-2">
                                    <button type="button" class="form-control btn btn-danger removeItem">Remove</button>       
                                </div>                              
                            </div>     
                        </div>
                        <div class="row-fluid">
                            <div class="col-sm-2 col-sm-push-10">
                                <button type="button" class="btn btn-primary addItem">New Item</button>
                            </div> 
                        </div>  
                    </div>
                </div>
                <%
                    selected = Truck.getTruckByID(Integer.parseInt(request.getParameter("truckName")));
                    out.print("<h2>" + selected.getTruckName() + "</h2>");
                    List<Menu> menu = selected.getMenus();
                    for (Menu m : menu) {
                        if (m == null) {
                            continue;
                        }
                        String menuName = m.getMenuName();
                        String menuDescription = "";

                        if (m.getDescription() != null) {
                            menuDescription = m.getDescription();
                        }

                        out.print("<div class='panel panel-default panelCloneWrapper'>"
                                + "<div class='panel-heading'>"
                                + "<input type='hidden' name='menuId' value='" + m.getId() +"'>"
                                + "<input type='text' class='form-control' name='categoryTitle' value='" + menuName + "'>"
                                + "<input type='text' class='form-control' name='categoryDescription' value='" + menuDescription + "'>"
                                + "<button type='button' class='form-control btn btn-danger' id='removeCategory'>Remove</button> "
                                + "</div>"
                                + "<div class='panel-body outerWrapper'>"
                                + "<div class ='container-fluid containerWrapper'>"
                                + "<div class='row-fluid'><div class='col-sm-3'>"
                                + "<h4>Name</h4>"
                                + "</div>"
                                + "<div class='col-sm-2'>"
                                + "<h4>Price</h4>"
                                + "</div>"
                                + "<div class='col-sm-5'>"
                                + "<h4>Tags</h4>"
                                + "</div>"
                                + "<div class='col-sm-2'>"
                                + "<h4>&nbsp;</h4"
                                + "></div>"
                                + "</div>");

                        Set<Item> items = m.getItems();
                        for (Item i : items) {
                            i = Item.getItemByID(i.getId(), false, true);
                            String itemName = "";
                            double itemPrice = 0;
                            if (i.getItemName() != null) {
                                itemName = i.getItemName();
                            }
                            if (i.getPrice() != 0) {
                                itemPrice = i.getPrice();
                            }
                            out.print("<div class='row-fluid cloneWrapper'>"
                                    +"<input type='hidden' name='itemId' value='" + i.getId() +"'>"
                                    + "<div class='col-sm-3' id='itemName'>"
                                    + "<input type='text' class='form-control' name='itemName' value='" + itemName + "'>"
                                    + "</div>"
                                    + "<div class='col-sm-2' id='itemPrice'>"
                                    + "<input type='text' class='form-control' name='itemPrice' value='" + itemPrice + "'>"
                                    + "</div>"
                                    + "<div class='col-sm-5' id='tags'>"
                                    + "<textarea class='form-control' style='min-width: 100%; margin-bottom: 5px;' rows='1' name='tags'>");
                            Set<Tag> tags = i.getTags();
                            for (Tag t : tags) {
                                out.println(t.getTagName() + ", ");
                            }
                            out.print("</textarea>"
                                    + "</div>"
                                    + "<div class='col-sm-2'>"
                                    + "<button type='button' class='form-control btn btn-danger removeItem'>Remove</button>       "
                                    + "</div>"
                                    + "</div>");
                        }

                        out.print("</div>"
                                + "<div class='row-fluid'>"
                                + "<div class='col-sm-2 col-sm-push-10'>"
                                + "<button type='button' class='btn btn-primary addItem'>New Item</button>"
                                + "</div>"
                                + "</div>"
                                + "</div>"
                                + "</div>");

                    }
                %>
            </div>
            <button type="button" class="btn btn-primary" id="addCategory">New Category</button>
        </fieldset>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
<%@ include file="footer.html"%>
<script src="cloneMenu.js"></script>
<script>
    $("#form").submit(function (e) {
        e.preventDefault();
        var name = $(e.target).find('[name=truckName]').val();
        var menuArray = $(e.target).find('.panel').not('.hidden').map(function () {
            var menu = new Object();
            var panelHeading = $(this).children('[class=panel-heading]');
            menu.id = panelHeading.children('[name=menuId]').val();
            menu.menuName = panelHeading.children("[name=categoryTitle]").val();
            menu.description = panelHeading.children("[name=categoryDescription]").val();
            menu.items = $(this).find('.row-fluid').filter('.cloneWrapper').map(function () {
                var item = new Object();
                item.id = $(this).children("[name=itemId]").val();
                item.itemName = $(this).children("[id=itemName]").children("[name=itemName").val();
                item.price = $(this).children("[id=itemPrice]").children("[name=itemPrice").val();
                item.tags = $(this).children("[id=tags]").children("[name=tags").val();
                return item;
            }).get();
            return menu;
        }).get();
        $.ajax({
            type: "POST",
            url: "/EditMenuServlet",
            dataType: 'JSON',
            data: {menu: JSON.stringify(menuArray), truckName: name},
            success: function (data) {
                alert(data);
            },
            error: function (error) {
                alert("There was an error.");
                console.log(error);
            }
        });
    });
</script>