package com.abfinance.api.examples.websocket.stream;

import com.abfinance.api.client.config.ABFinanceApiConfig;
import com.abfinance.api.client.service.ABFinanceApiClientFactory;

import java.util.List;

public class WebsocketDebuggerExample {
    public static void main(String[] args) {
        var client = ABFinanceApiClientFactory.newInstance(ABFinanceApiConfig.STREAM_TESTNET_DOMAIN, true).newWebsocketClient(20);

        // Orderbook
        client.getPublicChannelStream(List.of("orderbook.50.MATICUSDT"), ABFinanceApiConfig.V5_PUBLIC_LINEAR);
    }
}
