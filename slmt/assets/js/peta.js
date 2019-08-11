setInterval(function () {setMaps();}, 30000);

function setMaps() {
  var start_point, map, wtr, wfl;

  start_point = new google.maps.LatLng(0.5437088, 101.4431027);
  
  map = new google.maps.Map(document.getElementById("maps"), {
      center: start_point,
      zoom: 11,
      mapTypeId: google.maps.MapTypeId.ROADMAP
  });
  setPolygons(map);
  setMarkers1(map);
  
  wtr = new google.maps.Map(document.getElementById("maps1"), {
      center: start_point,
      zoom: 11,
      mapTypeId: google.maps.MapTypeId.ROADMAP
  });
  setPolygons(wtr);
  setMarkers2(wtr);

  wfl = new google.maps.Map(document.getElementById("maps2"), {
      center: start_point,
      zoom: 11,
      mapTypeId: google.maps.MapTypeId.ROADMAP
  });
  setPolygons(wfl);
  setMarkers3(wfl);
}

function setPolygons(map, polygon, marker) {
  $.ajax({
    type: 'GET',
    url: 'polygon',
    dataType: 'json',
    success: function(zones) {
        var pathCoordinates = [];
        var i, k, zone, perimeter, color, icon, lat, lon, latLng, marker, coords, recoords, polygon;

        for (i = 0; i < zones.polygon.length; i++) {
          zone = zones.polygon[i];
          name = zone.kode;
          perimeter = zone.latlon;
          color = zone.color;
          ico = zone.icon;
          lat = zone.tlat;
          lon = zone.tlon;

          latLng = new google.maps.LatLng(lat, lon);
          marker = new google.maps.Marker({
              position: latLng,
              map: map,
              icon: 'assets/images/icons/' + ico,
              title: name
          });

          coords = perimeter.split(" ");
            
          for (k = 0; k < coords.length; k++) {
            recoords = coords[k].split(",");
            pathCoordinates.push(new google.maps.LatLng(parseFloat(recoords[0]), parseFloat(recoords[1])));
          }

          polygon = new google.maps.Polygon({
              path: pathCoordinates,
              strokeColor: color,
              strokeOpacity: 0.8,
              strokeWeight: 3,
              fillColor: color,
              fillOpacity: 0.15,
              Name: name,
              map: map
          });
          pathCoordinates = [];
        }
    },
    error: function(zones) { console.log('Refresh Halaman'); }
  });
}

function setMarkers1(map, marker) {
  $.ajax({
    type: 'GET',
    url: 'marker1',
    dataType: 'json',
    success: function(data) {
        if (data.marker.length != 0) {
          $.each(data.marker, function(marker, data) {
              var latLon, marker, windowContent, infoWindow, geocd, gadd;

              latLon = new google.maps.LatLng(data.lat, data.lon);

              var kt = data.jns_pelanggaran.split(" ")
              marker = new google.maps.Marker({
                  position: latLon,
                  map: map,
                  icon: 'assets/images/icons/' + kt[0] + kt[1] + data.status + '.png',
                  animation: google.maps.Animation.DROP
              });

              geocd = new google.maps.Geocoder();
              geocd.geocode({ latLng : latLon }, function(results) {
                if (results && results.length > 0) {
                  windowContent = '<h5 ><center>' + data.jns_pelanggaran + '</center></h5>' + '<img src="assets/images/report/' + data.gambar + '" class="img img-thumbnail" alt="image" style="width:50%;height:50%;display:block;margin-left:auto; margin-right:auto;"/>' + '<p><center>' + data.keterangan + '</center></p>' + '<p><center>' + results[0].formatted_address + '</center></p>';

                  infoWindow = new google.maps.InfoWindow({
                    content: windowContent,
                    maxWidth: 300
                  });
                }
              });

              google.maps.event.addListener(marker, 'click', function() {
                  infoWindow.open(map, marker);
              });
          }); 
        }
    },
    error: function(data) { console.log('Refresh Halaman'); }
  });
}

function setMarkers2(map, marker) {
  $.ajax({
    type: 'GET',
    url: 'marker2',
    dataType: 'json',
    success: function(data) {
        if (data.marker.length != 0) {
          $.each(data.marker, function(marker, data) {
              var latLon, marker, windowContent, infoWindow, geocd, gadd;

              latLon = new google.maps.LatLng(data.lat, data.lon);

              var kt = data.jns_pelanggaran.split(" ")
              marker = new google.maps.Marker({
                  position: latLon,
                  map: map,
                  icon: 'assets/images/icons/' + kt[0] + kt[1] + data.status + '.png',
                  animation: google.maps.Animation.DROP
              });

              geocd = new google.maps.Geocoder();
              geocd.geocode({ latLng : latLon }, function(results) {
                if (results && results.length > 0) {
                  windowContent = '<h5><center>' + data.jns_pelanggaran + '</center></h5>' + '<img src="assets/images/report/' + data.gambar + '" class="img img-thumbnail" alt="image" style="width:50%;height:50%;display:block;margin-left:auto; margin-right:auto;"/>' + '<p><center>' + data.keterangan + '</center></p>' + '<p><center>' + results[0].formatted_address + '</center></p>';

                  infoWindow = new google.maps.InfoWindow({
                    content: windowContent,
                    maxWidth: 300
                  });
                }
              });

              google.maps.event.addListener(marker, 'click', function() {
                  infoWindow.open(map, marker);
              });
          }); 
        }
    },
    error: function(data) { console.log('Refresh Halaman'); }
  });
}

function setMarkers3(map, marker) {
  $.ajax({
    type: 'GET',
    url: 'marker3',
    dataType: 'json',
    success: function(data) {
        if (data.marker.length != 0) {
          $.each(data.marker, function(marker, data) {
              var latLon, marker, windowContent, infoWindow, geocd, gadd;

              latLon = new google.maps.LatLng(data.lat, data.lon);

              var kt = data.jns_pelanggaran.split(" ")
              marker = new google.maps.Marker({
                  position: latLon,
                  map: map,
                  icon: 'assets/images/icons/' + kt[0] + kt[1] + data.status + '.png',
                  animation: google.maps.Animation.DROP
              });

              geocd = new google.maps.Geocoder();
              geocd.geocode({ latLng : latLon }, function(results) {
                if (results && results.length > 0) {
                  windowContent = '<h5><center>' + data.jns_pelanggaran + '</center></h5>' + '<img src="assets/images/report/' + data.gambar + '" class="img img-thumbnail" alt="image" style="width:50%;height:50%;display:block;margin-left:auto; margin-right:auto;"/>' + '<p><center>' + data.keterangan + '</center></p>' + '<p><center>' + results[0].formatted_address + '</center></p>';

                  infoWindow = new google.maps.InfoWindow({
                    content: windowContent,
                    maxWidth: 300
                  });
                }
              });

              google.maps.event.addListener(marker, 'click', function() {
                  infoWindow.open(map, marker);
              });
          }); 
        }
    },
    error: function(data) { console.log('Refresh Halaman'); }
  });
}
 