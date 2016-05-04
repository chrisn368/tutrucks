<%@ page import="edu.temple.tutrucks.*, java.util.List, com.google.gson.Gson, com.google.gson.JsonArray" %>

<%--
For each truck matching search criteria:
put truck image in left-side of tile
put truck name in Title slot on right side
put truck location in location slot on right side
put rating in rating slot on right side
--%>
<%@ include file="header.jsp"%>    

<%@ include file="truckReviewModal.jsp"%>
<%
        String search = request.getParameter("criteria");
        String tagged = request.getParameter("tagged");
        List<Searchable> results = DBUtils.searchAll(search, tagged);
        Truck t;
        out.print("<div class='row'>");
        out.print("<div class='col-lg-1'> </div>");
        out.print("<div class='col-lg-10'>");
            out.print("<div class='panel panel-default'>\n");
                out.print("<div class='panel-heading'>\n");
                    out.print("<h1 class='panel-title'>");
                        out.print("Trucks");
                    out.print("</h1>\n");
                    out.print("<h5 style='font-style: italic'>");
                        out.print("Trucks matching search criteria");
                    out.print("</h5>\n");
                out.print("</div>\n");
                out.print("<div class='panel-body'>\n");
                    for (Searchable s : results) {
                        if (s instanceof Truck) {
                            t = (Truck) s;
                            out.print("<div class ='panel panel-danger'>\n");
                            out.print("<a href='truck.jsp?truck="+t.getId()+"' style='color: black'>");
                            out.print("<div class='panel-heading'>\n");
                                out.print("<h1 class='panel-title'>");
                                    out.print(t.getTruckName());
                                out.print("</h1>\n");
                            out.print("</a>");
                            out.print("</div>");
                        out.print("<div class='panel-body'> \n");
                            out.print("<div class='row-fluid'>\n");
                                out.print("<a href='truck.jsp?truck="+t.getId()+"' style='color: black'>");
                                    //out.print("<div class='col-md-4'></div>");
                                    out.print("<div class='col-lg-4 truckPhoto login'>");
                                        out.print("<img src='");
                                        String avatarURL= t.getAvatar();
                                        if(avatarURL==null||avatarURL.equals("")){
                                            out.print("images/NoTruckImage.png' alt='Default Photo'");
                                        }else out.print(avatarURL+"' alt='Photo of "+t.getTruckName()+"'");
                        
                                        out.print("width=160px height=120px>");
                                    out.print("</div>\n");

                                    out.print("<div class='col-lg-4 login' style='vertical-align: middle;'>");
                                        boolean morning=true;
                                        int openingHours=t.getOpeningTime().getHours();
                                        int openingMinutes=t.getOpeningTime().getMinutes();
                                        if (openingHours>12){
                                            morning =false;
                                            openingHours-=12;
                                        }if (openingHours==0) openingHours=12;
                                        out.print(openingHours+":"+openingMinutes);
                                        if(openingMinutes==0) out.print("0");
                                        if (morning)out.print(" AM");
                                        else out.print(" PM");
                                        out.print(" - ");
                                        int closingHours=t.getClosingTime().getHours();
                                        int closingMinutes=t.getClosingTime().getMinutes();
                                        if (closingHours>12){
                                            morning =false;
                                            closingHours-=12;
                                        }if (closingHours==0) closingHours=12;
                                        out.print(closingHours+":"+closingMinutes);
                                        if(closingMinutes==0) out.print("0");
                                        if (morning)out.print(" AM");
                                        else out.print(" PM");
                                    out.print("</div>\n");
                                out.print("</a>");
                                out.print("<div class='col-lg-4 click login' style='vertical-align: middle;' data-toggle='modal' data-target='#truckModal' data-truckid="
                                    +t.getId()
                                    + ">");
                                    t.loadReviews();
                                    int avgRating=t.getScore();
                                    int fullStars=avgRating/2;
                                    int halfStars=avgRating%2;
                                    out.print("Average Review: ");
                                    if (avgRating==0) out.print("None");
                                    for (int i=0;i<fullStars;i++){
                                        out.print("<img src='images/Star_Full.png' width='24' height='24'>");
                                    }
                                    if (halfStars==1){
                                        out.print("<img src='images/Star_Half.png' width='12' height='24'>");
                                    }


                                out.print("</div>\n");
                            out.print("</div>\n");

                        out.print("</div>\n");
                    out.print("</div>\n");
                        }
                    }
        out.print("</div>\n");
        out.print("</div>\n");
        out.print("</div>\n");
        out.print("<div class='col-md-2'> </div>");
        out.print("</div>");
        
        Item item;
        out.print("<div class='row'>");
        out.print("<div class='col-md-2'> </div>");
        out.print("<div class='col-md-8'>");
        out.print("<div class='panel panel-default'>\n");
        out.print("<div class='panel-heading'>\n");
        out.print("<h1 class='panel-title'>");
        out.print("Items");
        out.print("</h1>\n");
        out.print("<h5 style='font-style: italic'>");
        out.print("Items matching search criteria");
        out.print("</h5>\n");
        out.print("</div>\n");
        out.print("<div class='panel-body'>\n");
        for (Searchable s : results) {
            if (s instanceof Item) {
            item = (Item) s;
            out.print("<div class ='panel panel-danger'>\n");
                out.print("<div class='panel-heading'>\n");
                    out.print("<a href='truck.jsp?truck="+item.getMenu().getTruck().getId()+"' style='color: black'>");
                    out.print("<h1 class='panel-title'>");
                        out.print(item.getItemName());
                    out.print("</h1>\n");
                    out.print("</a>");
                out.print("</div>");
            out.print("<div class='panel-body'> \n");
                out.print("<div class='row-fluid'>\n");
                    out.print("<div class='col-lg-4 truckPhoto login'>");

                        out.print(item.getMenu().getTruck().getTruckName());
                    out.print("</div>\n");

                    out.print("<div class='col-lg-5 login'>");

                        out.print(item.getPrice());

                    out.print("</div>\n");
                    out.print("<div class='col-lg-3 click login' data-toggle='modal' data-target='#reviewmodal'>");

                        int avgRating=item.getScore();
                        int fullStars=avgRating/2;
                        int halfStars=avgRating%2;
                        out.print("Average Review: ");
                        for (int i=0;i<fullStars;i++){
                            out.print("<img src='images/Star_Full.png' width='24' height='24'>");
                        }
                        if (halfStars==1){
                            out.print("<img src='images/Star_Half.png' width='12' height='24'>");
                        }


                    out.print("</div>\n");
                out.print("</div>\n");

            out.print("</div>\n");
            out.print("</div>\n");
            }
        }
        out.print("</div>\n");
        out.print("</div>\n");
        out.print("</div>\n");
        out.print("<div class='col-lg-1'> </div>");
        out.print("</div>");
%>
<%@ include file="footer.html"%>