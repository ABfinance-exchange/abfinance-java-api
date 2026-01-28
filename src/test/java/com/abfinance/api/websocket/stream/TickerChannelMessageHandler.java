package com.abfinance.api.websocket.stream;

import com.abfinance.api.client.config.ABFinanceApiConfig;
import com.abfinance.api.client.domain.websocket_message.public_channel.WebSocketTickerMessage;
import com.abfinance.api.client.service.ABFinanceApiClientFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class TickerChannelMessageHandler {
    public static void main(String[] args) {
        var client = ABFinanceApiClientFactory.newInstance(ABFinanceApiConfig.STREAM_MAINNET_DOMAIN, true).newWebsocketClient();

        client.setMessageHandler(message -> {
            var tickerData = (new ObjectMapper()).readValue(message, WebSocketTickerMessage.class);
            // Process message data here
            System.out.println("Websocket Message Data: " + tickerData.getData().toString());
        });

        // Ticker
        client.getPublicChannelStream(List.of("tickers.BTCUSDT"), ABFinanceApiConfig.V5_PUBLIC_LINEAR);
    }
}
