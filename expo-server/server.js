var express = require("express");
var app = express();
var bodyParser = require("body-parser");
var http = require('http').Server(app);


var UserController = require('./controller/pushController.js');

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
  "extended": false,
}));
app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,x-access-token");
  res.header('Access-Control-Allow-Methods', 'PUT, POST, GET, DELETE, OPTIONS');
   next()
});


app.use('/sendPush',UserController);

http.listen(3000, function() {
  console.log('Listening port : 3000');
});


module.exports = app;