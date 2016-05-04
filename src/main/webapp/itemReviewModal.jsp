<%@ page import="java.util.List"%>
<%@ page import="edu.temple.tutrucks.*" %>

<div id="itemModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                
                <h4 class="modal-title">Reviews</h4>
            </div>
            <div id="itemreviews" class="modal-body">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<script>
    $('#itemModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var recipient = button.data('itemid'); // Extract info from data-* attributes
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this);
        var innerString="";
        $("#itemreviews").replaceWith("<div id='itemreviews' class='modal-body'> </div>");
        <% if (user==null)out.print("/*");%>
        innerString+="<div class='row'>";
        innerString+="<a style='color: black;' href=newreview.jsp?type=item&id="+recipient+">Click here to leave a review</a><br>";
        innerString+="</div>";
        innerString+="<hr />";
        <% if (user==null)out.print("*/");%>
        $.ajax("fetchItems", {
            method: "GET",
            dataType: "json",
            data: {criteria:recipient, start: 0, end: 20},
            success: function (data){
                
                for (var i=0;i<data["reviews"].length;i++){
                    //alert(data["reviews"][i]["user"]["avatar"]);
                    innerString+="<div class='row borders'>";
                    innerString+="<div class='row'>";
                    innerString+="<a href=profile.jsp?userid=";
                    innerString+=data["reviews"][i]["user"]["uid"];
                    innerString+=">";
                    innerString+="<img class = 'avatar' src='";
                    if (data["reviews"][i]["user"]["avatar"]== null||data["reviews"][i]["user"]["avatar"]=="") {
                        innerString+="images/NoUserPhoto.png";
                    }else innerString+=data["reviews"][i]["user"]["avatar"];
                    
                    innerString+="'alt='No user avatar' width='32px' height='32px'/> ";
                    innerString+="</a>";
                    avgRating=data["reviews"][i]["stars"];
                    fullStars=avgRating/2;
                    halfStars=avgRating%2;
                    innerString+="Rating: ";
                    if (avgRating===0){
                        innerString+="None";
                    }
                    for (c=0; c<fullStars; c++){
                        innerString+="<img src='images/Star_Full.png' width='24' height='24'>";
                    }
                    if (halfStars===1){
                        innerString+="<img src='images/Star_Half.png' width='12' height='24'>";
                    }
                    innerString+="</div>";
                    innerString+="<div class='row-fluid'>";
                    innerString+="<p style='word-wrap: break-word; padding-left=20px;padding-left=20px;'>";
                    innerString+=data["reviews"][i]["text"];
                    innerString+="</p>";
                    innerString+="</div>";
                    innerString+="<a href=profile.jsp?userid=";
                    innerString+=data["reviews"][i]["user"]["uid"];
                    innerString+=">";
                    innerString+="<div class='row userAndDate' style='color:black'>";
                    innerString+="Reviewed by ";
                    innerString+=data["reviews"][i]["user"]["name"];
                    innerString+=" on ";
                    innerString+=data["reviews"][i]["date"];
                    innerString+="</div>";
                    innerString+="</a>";
                    innerString+="<br />";
                    innerString+="</div>";
                    innerString+="<br />"
                    
                    
                }
                if (i===0){
                    innerString+="No Reviews";
                }
                $("#itemreviews").append(innerString);
            },
            error: function (jqXHR, status, error){
                console.log(error);
            }
        });
        
    });
</script>