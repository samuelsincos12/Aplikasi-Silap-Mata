window.setTimeout(function () {
    $("#alertsm").fadeTo(200, 0).slideUp(200, function () {
      $(this).remove();
    });
}, 5000);