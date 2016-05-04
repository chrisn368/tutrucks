<div id="registrationModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                
                <h4 class="modal-title">Create an Account</h4>
            </div>
            <div class="modal-body">
                <p>
                    <div class="row">
                        <form id="registrationForm" class="registration login" action="createUser" method="post">
                            <div class="col-lg-1">
                                &nbsp;
                            </div>
                            <div class="col-lg-4" style="text-align: center;">
                                <input type="email" id="email_r" pattern="(.+)@(.+)\.((com)|(edu)|(org)|(gov)|(net)|(io))" name="email" placeholder="Email" /><br />
                                <input type="password" id="password_r" pattern=".{6,16}" name="password" placeholder="Password (6-16 characters)" /><br />
                                <input type="hidden" id="facebook_id_r" name="facebook_id" />
                                <input type="hidden" id="display_r" name="display" />
                                <input type="hidden" id="avatar_r" name="avatar" />
                                <input type="hidden" id="currentpage_r" name="currentpage" />
                                <input id="registrationSubmit" type="submit" value="Create Account" />
                            </div>
                            <div class="col-lg-2">
                                <strong> OR </strong>
                            </div>
                            <div class="col-lg-4">
                                <div id="registrationButton"><img src="images/fbconnect.png" width="180px" height="40px"></div>
                                <script>
                                    $(document).ready(function () {
                                        $("#registrationButton").click(function() {
                                            FB.getLoginStatus(function(response) {
                                                if (response.status === 'connected') {
                                                    $('#modal').modal('toggle');
                                                    
                                                    var uid = response.authResponse.userID;
                                                    var email=response.authResponse.email;
                                                    
                                              } else if (response.status === 'not_authorized') {
                                                // the user is logged in to Facebook, 
                                                // but has not authenticated your app
                                              } else {
                                                FB.login(function(response) {
                                                    if (response.authResponse) {
                                                     FB.api('/me', { locale: 'en_US', fields: 'id, name, email, picture' }, function(response) {
                                                       document.getElementById("email_r").value = response.email;
                                                       document.getElementById("facebook_id_r").value = response.id;
                                                       document.getElementById("display_r").value=response.name;
                                                       document.getElementById("avatar_r").value=response.picture.data.url;
                                                       document.getElementById("currentpage_r").value=window.location.href;
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
