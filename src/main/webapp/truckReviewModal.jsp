<%@ page import="java.util.List"%>
<%@ page import="edu.temple.tutrucks.*" %>

<div id="truckModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                
                <h4 class="modal-title">Reviews</h4>
            </div>
            <div class="modal-body">
                <p>
                    <div class="row">
                    
                    </div>
                </p>
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
        $.ajax("fetchTrucks", {
            method: "GET",
            dataType: "json",
            data: {criteria:recipient, start: 0, end: 20},
            success: function (data){
                alert(data);
            },
            error: function (jqXHR, status, error){
                
            }
        });
        
    });
</script>