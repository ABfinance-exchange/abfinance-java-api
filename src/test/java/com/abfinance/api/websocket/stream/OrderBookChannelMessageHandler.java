package com.abfinance.api.websocket.stream;

import com.abfinance.api.client.config.ABFinanceApiConfig;
import com.abfinance.api.client.domain.websocket_message.public_channel.WebsocketOrderbookMessage;
import com.abfinance.api.client.service.ABFinanceApiClientFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class OrderBookChannelMessageHandler {
    public static void main(String[] args) {
        var client = ABFinanceApiClientFactory.newInstance(ABFinanceApiConfig.STREAM_MAINNET_DOMAIN, true).newWebsocketClient();

        client.setMessageHandler(message -> {
            var orderbookMessage = (new ObjectMapper()).readValue(message, WebsocketOrderbookMessage.class);
            // Process message data here
            System.out.println("Websocket Message Data: " + orderbookMessage.getData().toString());
        });

        // Order book
        client.getPublicChannelStream(List.of("orderbook.50.BTCUSDT"), ABFinanceApiConfig.V5_PUBLIC_LINEAR);
    }
}
