package com.abfinance.api.examples.websocket.stream;

import com.abfinance.api.client.config.ABFinanceApiConfig;
import com.abfinance.api.client.service.ABFinanceApiClientFactory;

import java.util.List;

public class WebsocketPrivateChannelExamples {
    public static void main(String[] args) {
        var client = ABFinanceApiClientFactory.newInstance("YOUR_API_KEY", "YOUR_API_SECRET", ABFinanceApiConfig.STREAM_TESTNET_DOMAIN, true).newWebsocketClient(5, "60s", (message) -> System.out.println("Handle message :" + message));
        // Position
        // client.getPrivateChannelStream(List.of("position"), ABFinanceApiConfig.V5_PRIVATE);

        // Order
        var webSocket = client.getPrivateChannelStream(List.of("order"), ABFinanceApiConfig.V5_PRIVATE);

        // Close websocket
        client.onClose(webSocket, 1000, "close normal");
    }
}
