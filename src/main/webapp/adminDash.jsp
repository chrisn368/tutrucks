<%@ include file="header.jsp"%>
<style>
    body {
        shadow: none;
        color: white;
    }
    .spacer {
        margin-top: 100%;
    }
    .thumbnail {
        position: absolute;
        top: 15px;
        bottom: 0;
        left: 15px;
        right: 0;
        text-align:center;
        padding-top:calc(20% - 30px);
        background-color: #A41E35;
        border: solid white 6px;
        font-weight: bold;
    }
    a.thumbnail:hover {
        text-decoration: none;
        color: #999;
        border: solid #999 6px;
    }
    h3{
        font-weight: bold;
    }


</style>
<div class="container">
    <h1>Admin Dashboard</h1>
    <div class="row">
        <div class="col-md-3 col-sm-4 col-xs-6">
            <div class="spacer"></div>
            <a class="thumbnail"href="addTruck.jsp"><h3>Add a Truck</h3>
                <i style="font-size: 4.5vw; padding-top: 10px;" class="glyphicon glyphicon-plus"></i></a>
        </div>
        <div class="col-md-3 col-sm-4 col-xs-6">
            <div class="spacer"></div>
            <a class="thumbnail"href="editTruck.jsp"><h3>Edit a Truck</h3>
                <i style="font-size: 4.5vw; padding-top: 10px;" class="glyphicon glyphicon-edit"></i></a>
        </div>
        <div class="col-md-3 col-sm-4 col-xs-6">
            <div class="spacer"></div>
            <a class="thumbnail"href="deleteTruck.jsp"><h3>Delete a Truck</h3>
                <i style="font-size: 4.5vw; padding-top: 10px;" class="glyphicon glyphicon-trash"></i></a>
        </div>
        <div class="col-md-3 col-sm-4 col-xs-6">
            <div class="spacer"></div>
            <a class="thumbnail"href="addTruckImage.jsp"><h3>Add a truck image</h3>
            <i style="font-size: 4.5vw; padding-top: 10px;" class="glyphicon glyphicon-picture"></i></a>
        </div>
        <div class="col-md-3 col-sm-4 col-xs-6">
            <div class="spacer"></div>
            <a class="thumbnail"href="deleteReviews.jsp"><h3>Delete Reviews</h3>
            <i style="font-size: 4.5vw; padding-top: 10px;" class="glyphicon glyphicon-thumbs-down"></i></a>
        </div>
        <div class="col-md-3 col-sm-4 col-xs-6">
            <div class="spacer"></div>
            <a class="thumbnail"href="editMenu.jsp"><h3>Edit Menu</h3>
                <i style="font-size: 4.5vw; padding-top: 10px;" class="glyphicon glyphicon-cutlery"></i></a>
        </div>
        <div class="col-md-3 col-sm-4 col-xs-6">
            <div class="spacer"></div>
            <a class="thumbnail"href="deleteTag.jsp"><h3>Delete a Tag</h3>
            <i style="font-size: 4.5vw; padding-top: 10px;" class="glyphicon glyphicon-tag"></i></a>
        </div>
    </div>
</div>
<%@ include file="footer.html"%>