var map;
var userLocation;
var truckLocation;
function initMap() {
    //MAP SETUP
    templeCenter = {lat: 39.981478, lng: -75.155124};
    truckLocation = {lat: truck.lat, lng: truck.lng};
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 18,
        center: truckLocation
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
    var marker = new google.maps.Marker({
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

function getDirections() {
    if (userLocation == null) {
        alert("Sorry, your location could not be found or was denied.");
    } else {
        var link = "http://maps.google.com/maps?saddr=" + userLocation.lat + "," + userLocation.lng + "&daddr=" + truckLocation.lat + "," + truckLocation.lng;
        window.open(link);
    }
}

$('#more').click(function () {
    if ($('button span').hasClass('glyphicon-chevron-down'))
    {
        $('#more').html('<strong><span class="glyphicon glyphicon-chevron-up"></span> Hide Map</strong>');
    } else
    {
        $('#more').html('<strong><span class="glyphicon glyphicon-chevron-down"></span> Show Map</strong>');
    }
});

$(window).resize(function () {
    var h = $(window).height();
    $('#map').css('height', (h * .40));
    map.setCenter(truckLocation);
}).resize();