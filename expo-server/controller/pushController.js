var express = require("express");
var app = express();
var router = express.Router();
const { Expo } = require('expo-server-sdk');

let expo = new Expo();
let messages = [];

router.route("/")
.get(function(req, res) {

    res.sendStatus(200);

})
.post(async function(req, res) {
    
    messages = [];

    var arr = req.body;
    arr.forEach(element => {
        if (!Expo.isExpoPushToken(element.token)) {
            console.error(`Push token ${element.token} is not a valid Expo push token`);
            //return;
        }

        var eq = JSON.parse(element.message);
    
        messages.push({
            to: element.token,
            sound: 'default',
            body: eq.location + " bölgensinde " + eq.eqCount + " kere  deprem oldu.",
            data: eq,
          })

    });

    let ticketChunk = await expo.sendPushNotificationsAsync(messages);  
    console.log(ticketChunk);
    res.sendStatus(200);
});

module.exports = router;