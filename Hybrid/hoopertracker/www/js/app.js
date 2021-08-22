var $ = Dom7;

var device = Framework7.getDevice();
var app = new Framework7({
  name: 'HooperTracker', // App name
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
      if (page.route.name === 'exercises') {
        getExercises();
      }
    }
  },
});
// Login Screen Demo
$('#my-login-screen .login-button').on('click', function () {
  var username = $('#my-login-screen [name="username"]').val();
  var password = $('#my-login-screen [name="password"]').val();

  // Close login screen
  app.loginScreen.close('#my-login-screen');

  // Alert username and password
  app.dialog.alert('Username: ' + username + '<br/>Password: ' + password);
});

// Get Exercises
function getExercises() {
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
    let email= 'ralph@test.com'

    fetch('https://www.ralphcuizon.be/hoopertracker/hybrid.php?user_email=' + email, opties).then((res) => res.json())
    .then(response => {
      console.log(response);

      let tlines='';
      for (let i in response) {

        tlines += `<div class="card">
        <div class="card-header">Category: ${response[i].category}</div>
        <div class="card-footer">Description: ${ response[i].description}</div>
        <div class="card-footer">Time: ${ response[i].time} min(s)</div>
        </div>`;
    }

    $("#eList").html(tlines);
    }).catch(error => console.log(error));

  return true
}