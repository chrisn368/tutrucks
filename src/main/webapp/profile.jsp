
<%@ include file="header.jsp"%>

<%@ page import="edu.temple.tutrucks.*"%>
<%@ page import="java.util.List"%>
<div class="container profile">
    <div class="col-md-4">
        <%

            User userProfile=User.loadUserByID(Integer.parseInt(request.getParameter("userid")),true);
            String avatar=null;
            List<TruckReview> truckReviews;
            List<ItemReview> itemReviews;
            boolean isThisUser = false;
            if (userProfile==null){
                response.sendRedirect("invalidPage.jsp");
                return;
            }else{
//            user = new User();
//            user.setAvatar("https://upload.wikimedia.org/wikipedia/en/3/38/Avatarjakeneytiri.jpg");
//            user.setDisplayName("Avatar");
            avatar = userProfile.getAvatar();
            //userProfile = userProfile.loadUserReviews();
            truckReviews = userProfile.getTruckReviews();
//            TruckReview tr = new TruckReview();
//            Truck t = Truck.getTruckByID(1);
//            tr.setTruck(t);
//            tr.setReviewed(t);
//            tr.setReviewText("best truck ever");
//            truckReviews.add(tr);
            itemReviews = userProfile.getItemReviews();
            isThisUser = userProfile.equals(user);
            }
        %>

        <img class = "avatar" src="
             <% 
                if (avatar != null) {
                    out.print(avatar);
                }else out.print("images/NoUserPhoto.png");
             %>" 
             alt="No user avatar" width="128px" height="128px" style="border:2px solid #FFFFFF; border-radius:5px" /> <br />
        <% 
                if (isThisUser) {
                    out.print("<a style='font-size: x-small;' id='image_upload' href='#'>Upload a profile picture...</a> <br />");
                }
        %>
        <h3 id="displayname" style="color: white;"><%=userProfile.getDisplayName()%></h3>
        <div>
            <% 
                if (isThisUser) {
                    out.print("<a style='font-size: x-small;' id='changedn'>Change display name...</a>");
                    out.print("<span id='cdnform' hidden><input type='text' id='ndn' /><input type='button' id='cdn' /></span>");
                }
            %>
        </div>
        <h5 style="color: white;"><%=truckReviews.size()%> truck reviews</h5>
        <h5 style="color: white;"><%=itemReviews.size()%> item reviews</h5>
        <% 
            if (isThisUser) {
                out.print("<a style='font-size: x-small;' id='changepw'>Change password...</a>");
                out.print("<div id='cpwform' hidden><input type='password' id='opw' /><br /><input type='password' id='npw' /><input type='cpw' /></div>");
            }
        %>
    </div>
    <div class="col-md-8">
        <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h1 class="panel-title">Truck Reviews</h1>
                </div>
                <div class="panel-body">
                    <div class ="container-fluid">   
                        
                        <% for (TruckReview review : truckReviews) { %>
                            
                            <div class="row review">
                                <div class="row">
                                    <div class="col-sm-12" style="text-align: center; color:#A41E35;">
                                        <a href="truck.jsp?truck=<%=review.getTruck().getId()%>" style="color:#A41E35;">
                                        <h4><%=review.getTruck().getTruckName()%></h4>
                                        </a>
                                    </div>
                                    <div class="col-sm-12" style="text-align: center;">
                                        <%
                                            
                                            int reviewStars=review.getReviewStars();
                                            int fullStars=reviewStars/2;
                                            int halfStars=reviewStars%2;
                                            if (reviewStars==0){
                                                out.print("0-Star Review");
                                            }
                                            for (int c=0; c<fullStars; c++){
                                                out.print("<img src='images/Star_Full.png' width='24' height='24'>");
                                            }
                                            if (halfStars==1){
                                                out.print("<img src='images/Star_Half.png' width='12' height='24'>");
                                            }
                                        %>
                                    </div>
                                </div>
                                <div class="row" style="text-align: left; padding: 5px;">
                                    
                                    <p><%=review.getReviewText()%></p>
                                </div>
                                <div class="row userAndDate" style="text-align: center; padding: 5px;">
                                    <p>Reviewed on <%= review.getReviewDate()%></p>
                                </div>
                            </div>
                            <hr />
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h1 class="panel-title">Item Reviews</h1>
                </div>
                <div class="panel-body">
                    <div class ="container-fluid">
                        <% for (ItemReview review : itemReviews) { %>
                            <div class="row review">
                                <div class="row">
                                    <div class="col-sm-12" style="text-align: center; color:#A41E35;">
                                        
                                        <h4><%=review.getItem().getItemName()%>
                                            at
                                        <a href="truck.jsp?truck=<%=review.getItem().getMenu().getTruck().getId()%>" style="color:#A41E35;"><%=review.getItem().getMenu().getTruck().getTruckName()%></a></h4>
                                        
                                        
                                    </div>
                                    <div class="col-sm-12" style="text-align: center;">
                                        <%
                                            
                                            int reviewStars=review.getReviewStars();
                                            int fullStars=reviewStars/2;
                                            int halfStars=reviewStars%2;
                                            if (reviewStars==0){
                                                out.print("0-Star Review");
                                            }
                                            for (int c=0; c<fullStars; c++){
                                                out.print("<img src='images/Star_Full.png' width='24' height='24'>");
                                            }
                                            if (halfStars==1){
                                                out.print("<img src='images/Star_Half.png' width='12' height='24'>");
                                            }
                                        %>
                                    </div>
                                </div>
                                <div class="row" style="text-align: left; padding: 5px;">
                                    
                                    <p><%=review.getReviewText()%></p>
                                </div>
                                <div class="row userAndDate" style="text-align: center; padding: 5px;">
                                    <p>Reviewed on <%= review.getReviewDate()%></p>
                                </div>
                            </div>
                            <hr />
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
 <script>
    $(document).ready(function() {
        if (<%= isThisUser %>) {
            $("#image_upload").click(function() {
               // open image upload modal 
            });
            $("#changedn").click(function() {
               $("#changedn").hide();
               $("#cdn").val('Cancel');
               $("#cdnform").show();
            });
            $("#cdn").click(function() {
                var changeDisplayName = $("#ndn").val();
                if (changeDisplayName) {
                    $.ajax("edituserinfo", {
                       method: "POST",
                       dataType: "text",
                       data: { displayname: changeDisplayName },
                       success: function (data) {
                           $("#displayname").text(data);
                       }
                    });
                    $("#ndn").val("");
                    $("#cdn").val("Cancel");
                    $("#changedn").show();
                    $("#cdnform").hide();
                }
            });
            $("#ndn").keyup(function() {
                var changeTextToAdd = $(this).val().length > 0;
                $("#cdn").val(changeTextToAdd ? 'Submit' : 'Cancel');  
            });
            $("#changepw").click(function() {
                $("#changepw").hide();
                $("#cpw").val('Cancel');
                $("#cpwform").show();
            });
            $("input:password").keyup(function() {
                var changeTextToAdd = true;
                $(this).each(function(index, element) {
                    changeTextToAdd = changeTextToAdd && $(this).val().length > 0;
                });
                $("#cpw").val(changeTextToAdd ? 'Submit' : 'Cancel');  
            });
            $("#cpw").click(function() {
                var oldPassword = $("#opw").val();
                var changePassword = $("#npw").val();
                if (oldPassword && changePassword) {
                    $.ajax("edituserinfo", {
                       method: "POST",
                       data: { oldpassword: oldPassword, newpassword: changePassword },
                       success: function (data) {
                           alert(data);
                       }
                    });
                    $("#ndn").val("");
                    $("#cdn").val("Cancel");
                    $("#changedn").show();
                    $("#cdnform").hide();
                }
            });
        }
    });
</script>
<%@ include file="footer.html"%>
