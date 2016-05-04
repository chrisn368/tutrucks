/* global google */

var map;
var userLocation;
var templeCenter;
var trucks;

function intialize(openTrucks) {
    trucks = openTrucks;
    
    //MAP SETUP
    templeCenter = {lat: 39.981478, lng: -75.155124};
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 15,
        center: templeCenter
    });
    
    google.maps.event.addDomListener(window, "resize", function () {
        intialize(trucks);
    });
    
    //GET USER LOCATION
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            userLocation = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            addUserMarker();
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
    for (var i = 0; i < trucks.length; i++) {
        var marker = createMarker(trucks[i]);
        //HAVE TO PASS THE LAT AND LONG IN THE GET DIRECTIONS STRING
        var contentString = "<h5 style='color: black'>" + trucks[i].name + "</h5>" +
        "<button class='btn btn-sm' onclick='getDirections(" + trucks[i].lat + ", " + trucks[i].lng + ")' style='color: black'>Directions</button> <a href='truck.jsp?truck=" + trucks[i].id + "' style='color:black'>View Menu</a>";
        //ADD INFO WINDOW TO MARKER
        bindwindow(marker, map, infowindow, contentString);
        marker.setMap(map);
    }
}
;

function addUserMarker() {
    //USER MARKER
    var image = 'https://cdn0.iconfinder.com/data/icons/project-management-1-1/24/46-48.png';
    var userMarker = new google.maps.Marker({
        position: {lat: userLocation.lat, lng: userLocation.lng},
        animation: google.maps.Animation.DROP,
        title: "Your Location",
        icon: image
    });
    userMarker.setMap(map);
    map.setCenter(userLocation);
}
;

function createMarker(truck) {
    //MARKER
    var marker = new google.maps.Marker({
        position: {lat: truck.lat, lng: truck.lng},
        animation: google.maps.Animation.DROP,
        title: truck.name
    });
    return marker;
}
;

function bindwindow(marker, map, infowindow, content) {
    marker.addListener('click', function () {
        infowindow.setContent(content);
        infowindow.open(map, marker);
    });
}
;

function getDirections(endLat, endLng) {
    if (userLocation == null) {
        alert("Sorry, your location could not be found or was denied.");
    }
    else {
        var link = "http://maps.google.com/maps?saddr=" + userLocation.lat + "," + userLocation.lng + "&daddr=" + endLat + "," + endLng;
         window.open(link);
    }
}