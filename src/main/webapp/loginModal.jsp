<div id="loginModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                
                <h4 class="modal-title">Login</h4>
            </div>
            <div class="modal-body">
                <p>
                    <div class="row">
                        <form id="loginForm" class="login" action="/login" method="post">
                            <div class="col-lg-1">
                                &nbsp;
                            </div>
                            <div class="col-lg-4" style="text-align: center;">
                                <input type="email" pattern="(.+)@(.+)\.((com)|(edu)|(org)|(gov))" name="email" placeholder="Email" required /><br />
                                <input type="password" pattern=".{6,16}" name="password" placeholder="Password" /><br />
                                <input type="hidden" id="facebook_id" name="fbID" />
                                <input type="hidden" id="display" name="display" />
                                <input type="hidden" id="avatar" name="avatar" />
                                <input type="hidden" id="redirect" name="redirect" />
                                <input type="submit" id="submitButton" value="Log In" />
                            </div>
                            <div class="col-lg-2">
                                <strong> OR </strong>
                            </div>
                            <div class="col-lg-4">
                                <div id="loginButton"><img src="images/fbconnect.png" width="180px" height="40px"></div>
                                <script>
                                    $(document).ready(function () {
                                        $("#loginButton").click(function() {
                                            $("#redirect").val(window.location.href);
                                            FB.getLoginStatus(function(response) {
                                                if (response.status === 'connected') {
                                                FB.api('/me', { locale: 'en_US', fields: 'id, name, email, picture' }, function(response) {
                                                       document.getElementById("email").value = response.email;
                                                       document.getElementById("facebook_id").value = response.id;
                                                       document.getElementById("display").value=response.name;
                                                       document.getElementById("avatar").value=response.picture.data.url;
                                                       ("#submitButton").click();
                                                });
                                                //var uid = response.authResponse.userID;
                                                //var accessToken = response.authResponse.accessToken;
                                              } else if (response.status === 'not_authorized') {
                                                // the user is logged in to Facebook, 
                                                // but has not authenticated your app
                                              } else {
                                                FB.login(function(response) {
                                                    if (response.authResponse) {
                                                     FB.api('/me', { locale: 'en_US', fields: 'id, name, email, picture' }, function(response) {
                                                       document.getElementById("email").value = response.email;
                                                       document.getElementById("facebook_id").value = response.id;
                                                       document.getElementById("display").value=response.name;
                                                       document.getElementById("avatar").value=response.picture.data.url;
                                                     });
                                                    } else {
                                                     console.log('User cancelled login or did not fully authorize.');
                                                    }
                                                }, {scope: 'email, public_profile'});
                                              }
                                            });
                                        });
                                    });
                                    
                                    
                                    
                                </script>
                            </div>
                        </form>
                    </div>
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
            </div>
        </div>
    </div>
</div>
