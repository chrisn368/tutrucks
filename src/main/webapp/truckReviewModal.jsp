<%@ page import="java.util.List"%>
<%@ page import="edu.temple.tutrucks.*" %>

<div id="truckModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                
                <h4 class="modal-title">Reviews</h4>
            </div>
            <div id="reviews" class="modal-body">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<script>
    $('#truckModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var recipient = button.data('truckid'); // Extract info from data-* attributes
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this);
        var innerString="";
        $("#reviews").replaceWith("<div id='reviews' class='modal-body'> </div>");
        $.ajax("fetchTrucks", {
            method: "GET",
            dataType: "json",
            data: {criteria:recipient, start: 0, end: 20},
            success: function (data){
                
                for (var i=0;i<data.length;i++){
                    innerString.append("<div class='container'>");
                    innerString.append("<div class='row'>");
                    innerString.append("<div class='row'>");
                    innerString.append("<div class='col'>");
                    //Profile Picture
                    innerString.append("</div>");
                    innerString.append("<div class='col'>");
                    avgRating=[data[i]["stars"]];
                    fullStars=avgRating/2;
                    halfStars=avgRating%2;
                    innerString.append("Reviews: ");
                    if (avgRating===0){
                        innerString.append("None");
                    }
                    for (c=0; c<fullStars; c++){
                        innerString.append("<img src='images/Star_Full.png' width='24' height='24'>");
                    }
                    if (halfStars===1){
                        innerString.append("<img src='images/Star_Half.png' width='12' height='24'>");
                    }
                    innerString.append("</div>");
                    innerString.append("</div>");
                    innerString.append("<div class='row'>").append([data[i]["text"]]).append("</div>");
                    innerString.append("<div class='row panel-title'>").append([data[i]["date"]]).append("</div>");
                    innerString.append("</div>");
                    innerString.append("</div>");
                    
                }
                if (i===0){
                    innerString.append("NONE");
                }
                $("#reviews").append(innerString);
            },
            error: function (jqXHR, status, error){
                
            }
        });
        
    });
</script>