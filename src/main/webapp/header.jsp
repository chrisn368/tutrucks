
<%@page import="edu.temple.tutrucks.Permissions"%>
<!DOCTYPE html>
<%@page import="edu.temple.tutrucks.User"%>
<% 
    boolean invalidLogin = false;
    try {
        invalidLogin = Boolean.parseBoolean(request.getParameter("invalid"));
    } catch (Exception e) {}
    User user = (User) session.getAttribute("user");
    String logOffAreaVisibility, logOnAreaVisibility, adminAreaVisibility;
    adminAreaVisibility=logOnAreaVisibility=logOffAreaVisibility=" display:none ";
    int uid=0;
    if (user == null) {
        logOnAreaVisibility = " display:inline-block; ";
        logOffAreaVisibility ="display: none;";
        adminAreaVisibility = " display:none; ";
    }else{
        logOnAreaVisibility = " display:none; ";
        logOffAreaVisibility = " display:inline-block; ";
        uid=user.getId();
        if (user.getPermissions()==Permissions.ADMIN){
            adminAreaVisibility = " display: inline-block;";
        }else{
            adminAreaVisibility = " display:none; ";
        }
    }
%>
<html lang="en">
  <head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
    <script>window.jQuery || document.write('<script src="js/vendor/jquery.min.js"><\/script>')</script>
    <script>
        $(document).ready(function() {
        function facebookReady() {
            FB.init({
                appId      : '1272882256060359',
                xfbml      : true,
                status   : true,
                cookie     : true,
                version    : 'v2.5'
            });
            $(document).trigger("facebook:ready");
        }

        if(window.FB) {
            facebookReady();
        } else {
            window.fbAsyncInit = facebookReady;
        }

    
      (function(d, s, id){
         var js, fjs = d.getElementsByTagName(s)[0];
         if (d.getElementById(id)) {return;}
         js = d.createElement(s); js.id = id;
         js.src = "https://connect.facebook.net/en_US/sdk.js";
         fjs.parentNode.insertBefore(js, fjs);
       }(document, 'script', 'facebook-jssdk'));
   });
    </script>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">

    <title>TUtrucks</title>
    
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/cover.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="js/ie-emulation-modes-warning.js"></script>
    
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  
  <body>
      
      
    <%@include file="registrationModal.jsp" %>
    <%@include file="loginModal.jsp" %>
   <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">TUtrucks</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="index.jsp">Home</a></li>
            <li><a href="search.jsp?criteria=truck:*">All Trucks</a></li>
            <li><a href="profile.jsp?userid=<%=uid%>" style="<%=logOffAreaVisibility%>">My Profile</a></li>
            <li><a href="adminDash.jsp" style="<%=adminAreaVisibility%>">Admin Panel</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li class="dropdown" id="loginDropdown" style="<%=logOnAreaVisibility%>">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Login/Register <span class="caret"></span></a>
              <ul class="dropdown-menu" id="LoginDisplay">
                  <br>
                  
		<li id="loginItem">
                    <div class='col-lg-3 click login' data-toggle='modal' data-target='#loginModal'>Login</div>
                </li>
                <br>
                  
                <li id="registrationItem">
                    <div class='col-lg-3 click login' data-toggle='modal' data-target='#registrationModal'>Register</div>
                </li>
              </ul>
              
            </li>
            <li class="" id="logoutTab" style="<%=logOffAreaVisibility%>">
                    <a style="color:black" href="javascript:$.post('logout', { success: function(data) { window.location.href ='index.jsp'; } });">Logout</a>
            </li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    <div class="site-wrapper">
		
      <div class="site-wrapper-inner">
