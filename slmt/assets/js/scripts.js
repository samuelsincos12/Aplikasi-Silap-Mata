window.setTimeout(function () {
    $("#alertsm").fadeTo(200, 0).slideUp(200, function () {
      $(this).remove();
    });
}, 5000);


$(document).ready(function() {
  $('#tugas').DataTable();
});

function tampilkanPreview(gambar,idpreview){
  var gb, gbPreview, imageType, preview, reader, i;

  gb = gambar.files;              
  for (i = 0; i < gb.length; i++){
    gbPreview = gb[i];
    imageType = /image.*/;
    preview=document.getElementById(idpreview);            
    reader = new FileReader();       
    if (gbPreview.type.match(imageType)) {
      preview.file = gbPreview;
      reader.onload = (function(element) { 
        return function(e) { 
          element.src = e.target.result; 
        }; 
      })(preview);
      reader.readAsDataURL(gbPreview);
    } else {
      alert("Tipe file tidak sesuai.");
    }
  }    
}

function vAdd() {
    var lat = document.getElementById("lat").value;
    var lon = document.getElementById("lon").value;
    var id =  document.getElementById("id").value;
    var geocd, yl; 
    geocd = new google.maps.Geocoder();
    yl = new google.maps.LatLng(lat, lon);

    geocd.geocode({ latLng: yl }, function (results, status) {
    if(status == google.maps.GeocoderStatus.OK) {
      if(results[0]) {
        $('#results').html('<p>' + results[0].formatted_address + '</p>');
      } else { error('Google did not return any results.'); }
    } else { error("Reverse Geocoding failed due to: " + status); }
  });
}