<!--Need to set image size for avatars-->
<%@ include file="header.jsp"%>
<%@ page import="edu.temple.tutrucks.*"%>
<%@ page import="java.util.List"%>
<div class="container profile">
    <div class="col-md-4">
        <%
            User userProfile=new User(); //= (User) request.getParameter("userid");
//            user = new User();
//            user.setAvatar("https://upload.wikimedia.org/wikipedia/en/3/38/Avatarjakeneytiri.jpg");
//            user.setDisplayName("Avatar");
            String avatar = userProfile.getAvatar();
            List<TruckReview> truckReviews = userProfile.getTruckReviews();
//            TruckReview tr = new TruckReview();
//            Truck t = Truck.getTruckByID(1);
//            tr.setTruck(t);
//            tr.setReviewed(t);
//            tr.setReviewText("best truck ever");
//            truckReviews.add(tr);
            List<ItemReview> itemReviews = userProfile.getItemReviews();
        %>

        <img class = "avatar" src="
             <% 
                if (avatar != null) {
                    out.print(avatar);
                }
             %>" 
             alt="No user avatar">

        <h3><%=userProfile.getDisplayName()%></h3>
        <h5><%=truckReviews.size()%> truck reviews</h5>
        <h5><%=itemReviews.size()%> item reviews</h5>
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
                            <!--copied from truckReview.jsp-->
                            <div class="row review">
                                <div class="row">
                                    <div class="col-md-8" style="text-align: left; color:#A41E35;">
                                        <h4><%=review.getTruck().getTruckName()%></h4>
                                    </div>
                                    <div class="col-md-4" style="text-align: right;">
                                        <h5><%=review.getReviewStars()%></h5>
                                    </div>
                                </div>
                                <div class="row" style="text-align: left; padding: 5px;">
                                    <p><%=review.getReviewText()%></p>
                                </div>
                            </div>  
                            <!--end truckReview.jsp-->
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
                                    <div class="col-md-8" style="text-align: left; color:#A41E35;">
                                        <h4><%=review.getItem().getItemName()%>/h4>
                                    </div>
                                    <div class="col-md-4" style="text-align: right;">
                                        <h5><%=review.getReviewStars()%></h5>
                                    </div>
                                </div>
                                <div class="row" style="text-align: left; padding: 5px;">
                                    <p><%=review.getReviewText()%></p>
                                </div>
                            </div>  
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.html"%>
