#!/usr/bin/env node

var WebSocketClient = require('websocket').client;

var client = new WebSocketClient();

client.on('connectFailed', function(error) {
    console.log('Connect Error: ' + error.toString());
});

var cont = 0;

var nameMarket = ["EUR/JPY", "GBP/USD", "USD/EUR"]

client.on('connect', function(connection) {
    console.log('WebSocket Client Connected');
    connection.on('error', function(error) {
        console.log("Connection Error: " + error.toString());
    });
    connection.on('close', function() {
        console.log('Connection Closed');
    });
    connection.on('message', function(message) {
        if (message.type === 'utf8') {
            console.log("Received: '" + message.utf8Data + "'");
        }
    });

    function getRandomArbitrary(min, max) {
        return Math.random() * (max - min) + min;
    }

    function sendPrice() {
        if (connection.connected) {


            let price = getRandomArbitrary(10, 9999) + getRandomArbitrary(0, 5.5)
            let market = cont + "," + nameMarket[Math.round(getRandomArbitrary(0, nameMarket.length - 1))] + "," + price + "," + price + "," + new Date().toISOString().replace('T', ' ').replace('Z', '').replace('.', ':');

            connection.sendUTF(market);
            setTimeout(sendPrice, 1000);
            cont++;
        }
    }
    sendPrice();
});

client.connect('ws://127.0.0.1:8080/marketPriceListen', "", "http://127.0.0.1:8080");