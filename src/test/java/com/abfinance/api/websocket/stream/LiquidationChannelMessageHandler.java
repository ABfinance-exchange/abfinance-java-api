package com.abfinance.api.websocket.stream;

import com.abfinance.api.client.config.ABFinanceApiConfig;
import com.abfinance.api.client.domain.websocket_message.public_channel.WebSocketLiquidationMessage;
import com.abfinance.api.client.service.ABFinanceApiClientFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class LiquidationChannelMessageHandler {
    public static void main(String[] args) {
        var client = ABFinanceApiClientFactory.newInstance(ABFinanceApiConfig.STREAM_MAINNET_DOMAIN, true).newWebsocketClient();

        client.setMessageHandler(message -> {
            var liquidationData = (new ObjectMapper()).readValue(message, WebSocketLiquidationMessage.class);
            // Process message data here
                System.out.println("Websocket Message Data: " + liquidationData.getData().toString());
        });

        // Liquidation
        client.getPublicChannelStream(List.of("liquidation.BTCUSDT"), ABFinanceApiConfig.V5_PUBLIC_LINEAR);
    }
}
