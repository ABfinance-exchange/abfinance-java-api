package com.abfinance.api.websocket.stream;

import com.abfinance.api.client.config.ABFinanceApiConfig;
import com.abfinance.api.client.domain.websocket_message.private_channel.WebSocketOrderMessage;
import com.abfinance.api.client.service.ABFinanceApiClientFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class OrderChannelMessageHandler {
    public static void main(String[] args) {
        var client = ABFinanceApiClientFactory.newInstance("YOUR_API_KEY", "YOUR_API_SECRET", ABFinanceApiConfig.STREAM_TESTNET_DOMAIN, true)
                .newWebsocketClient(5, "60s", (message) -> {
                    var orderMessage = (new ObjectMapper()).readValue(message, WebSocketOrderMessage.class);
                    // Process message data here
                    System.out.println("Websocket Message Data: " + orderMessage.getData().toString());
                });

        // Order
        client.getPrivateChannelStream(List.of("order"), ABFinanceApiConfig.V5_PRIVATE);
    }
}
