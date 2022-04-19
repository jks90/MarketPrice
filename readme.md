

# client websocket

(Requisites)
https://github.com/vi/websocat


websocat ws://127.0.0.1:8080/marketPriceListen
107, EUR/JPY, 119.60,119.90,01-06-2020 12:01:02:002
108, GBP/USD, 1.2500,1.2560,01-06-2020 12:01:02:002
109, GBP/USD, 1.2499,1.2561,01-06-2020 12:01:02:100
110, EUR/JPY, 119.61,119.91,01-06-2020 12:01:02:110


# Bot prices

-RUN

cd bot
node feedPriceBot.js

# Swagger

http://localhost:8080/swagger-ui.html

The bot date pattern is:

2022-04-19 12:20:29.807
yyyy-MM-dd HH:mmm:ss:SSS

from documentation:

01-06-2020 12:01:02:100
dd-MM-yyyy HH:mmm:ss:SSS

change line 99/100 WsServerMarketPrice for testing 


