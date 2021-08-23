var $ = Dom7;

var device = Framework7.getDevice();
var app = new Framework7({
  name: 'HooperTracker H', // App name
  theme: 'auto', // Automatic theme detection
  el: '#app', // App root element

  id: 'be.cuizon.hoopertracker', // App bundle ID
  // App store
  store: store,
  // App routes
  routes: routes,


  // Input settings
  input: {
    scrollIntoViewOnFocus: device.cordova && !device.electron,
    scrollIntoViewCentered: device.cordova && !device.electron,
  },
  // Cordova Statusbar settings
  statusbar: {
    iosOverlaysWebView: true,
    androidOverlaysWebView: false,
    androidBackgroundColor:'#FF9800'
  },
  on: {
    init: function () {
      var f7 = this;
      if (f7.device.cordova) {
        // Init cordova APIs (see cordova-app.js)
        cordovaApp.init(f7);
      }
    },
    pageInit: function (page) {

    }
  },
});
// Log out btn
$("#idSignOut").on('click', function () {

  app.dialog.alert("clicked");


});


// Login Screen Demo
$('#my-login-screen .login-button').on('click', function () {
  var password = $('#my-login-screen [name="password"]').val();
  var email = $('#my-login-screen [name="email"]').val();
  
if(email!='' && password!=''){
    fetch('https://www.ralphcuizon.be/hoopertracker/login.php?email=' + email + '&password=' + password).then((res) => res.json())
    .then(response => {
      if (response == '1') {
        $("#btnSignIn").addClass('disabled');
        app.dialog.alert("Successfully logged in", "Login");
        getExercises(email);
        getUserInfo(email);
          // Close login screen
        app.loginScreen.close('#my-login-screen');
      }
      else {
        app.dialog.alert("Unsuccessfully logged in", "Login");
      }
    }).catch(error => console.log(error));
  }
  else {
    app.dialog.alert("Unsuccessfully logged in", "Login");
  }




  
});

// Get Exercises
function getExercises(email) {
  var opties = {
    method: "POST", // *GET, POST, PUT, DELETE, etc.
    mode: "cors", // no-cors, *cors, same-origin
    cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
    credentials: "omit", // include, *same-origin, omit
    headers: {
      "Content-Type": "application/json",
      "Accept": "application/json"
    }
  };

    // body data type must match "Content-Type" header
    opties.body = JSON.stringify({
      format: "json",
      table: "exercises",
      bewerking: "get"
    }); 

    fetch('https://www.ralphcuizon.be/hoopertracker/get_exercises.php?user_email=' + email, opties).then((res) => res.json())
    .then(response => {

      let tlines='';
      for (let i in response) {

        tlines += `<div class="card">
        <div class="card-header">Category: ${response[i].category}</div>
        <div class="card-footer">Description: ${ response[i].description}</div>
        <div class="card-footer">Time: ${ response[i].time} min(s)</div>
        </div>`;
    }

    $("#eList").html(tlines);
    $("#text").html('All Exercises');
    }).catch(error => console.log(error));

  return true
}

function getUserInfo(email) {
    fetch('https://www.ralphcuizon.be/hoopertracker/get_user.php?email=' + email).then((res) => res.json())
    .then(response => {

$('#my-profile [name="email"]').val(response[0].email);
$('#my-profile [name="firstname"]').val(response[0].firstname);
$('#my-profile [name="lastname"]').val(response[0].lastname);
$('#idSignOut').removeClass('disabled');


    }).catch(error => console.log(error));

  return true
}

