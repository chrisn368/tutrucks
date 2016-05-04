var map;
var userLocation;
var truckLocation;
var marker;
function initMap() {
    //MAP SETUP
    templeCenter = {lat: 39.981478, lng: -75.155124};
    truckLocation = {lat: truck.lat, lng: truck.lng};
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 18,
        center: truckLocation
    });
    
    google.maps.event.addDomListener(window, "resize", function () {
        reinitialize();
    });
    
    //GET USER LOCATION
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            userLocation = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            //addUserMarker();
        }, function (error) {
            //USER LOCATION NOT FOUND
            //userLocation = {lat: 39.981478, lng: -75.155124};
            console.log(error);
        });
    } else {
        //BROWSER DOESN'T SUPPORT GEOLOCATION
        //userLocation = {lat: 39.981478, lng: -75.155124};
    }

    var infowindow = new google.maps.InfoWindow();
    marker = new google.maps.Marker({
        position: {lat: truck.lat, lng: truck.lng},
        animation: google.maps.Animation.DROP,
        title: truck.name
    });

    var contentString = "<h5 style='color: black'>" + truck.name + "</h5>";
    marker.addListener('click', function () {
        infowindow.setContent(content);
        infowindow.open(map, marker);
    });
    marker.setMap(map);

}
;
    //google.maps.event.addDomListener(window, 'load', initMap);
    
function reinitialize() {
    google.maps.event.trigger(map, "resize");
    marker.setMap(map);
    map.setCenter(truckLocation);
}
    
function getDirections() {
    if (userLocation == null) {
        alert("Sorry, your location could not be found or was denied.");
    } else {
        var link = "http://maps.google.com/maps?saddr=" + userLocation.lat + "," + userLocation.lng + "&daddr=" + truckLocation.lat + "," + truckLocation.lng;
        window.open(link);
    }
}

function toggleClass() {
    if ($('button span').hasClass('glyphicon-chevron-down'))
    {
        $('#more').html('<strong><span class="glyphicon glyphicon-chevron-up"></span> Hide Map</strong>');
        $( "#collapseMap" ).slideToggle( "slow" );
        reinitialize();
    } else
    {
        $('#more').html('<strong><span class="glyphicon glyphicon-chevron-down"></span> Show Map</strong>');
        $( "#collapseMap" ).slideToggle( "slow" );
        reinitialize();
        //google.maps.event.trigger(map, "resize");
    }
}
