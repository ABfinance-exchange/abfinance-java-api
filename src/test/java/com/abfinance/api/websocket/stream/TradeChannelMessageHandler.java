package com.abfinance.api.websocket.stream;

import com.abfinance.api.client.config.ABFinanceApiConfig;
import com.abfinance.api.client.domain.websocket_message.public_channel.WebSocketTickerMessage;
import com.abfinance.api.client.domain.websocket_message.public_channel.WebSocketTradeMessage;
import com.abfinance.api.client.service.ABFinanceApiClientFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class TradeChannelMessageHandler {
    public static void main(String[] args) {
        var client = ABFinanceApiClientFactory.newInstance(ABFinanceApiConfig.STREAM_MAINNET_DOMAIN, true).newWebsocketClient();

        client.setMessageHandler(message -> {
            var tradeData = (new ObjectMapper()).readValue(message, WebSocketTradeMessage.class);
            // Process message data here
            System.out.println("Websocket Message Data: " + tradeData.getData().toString());
        });

        // Trade
        client.getPublicChannelStream(List.of("publicTrade.BTCUSDT"), ABFinanceApiConfig.V5_PUBLIC_LINEAR);
    }
}
