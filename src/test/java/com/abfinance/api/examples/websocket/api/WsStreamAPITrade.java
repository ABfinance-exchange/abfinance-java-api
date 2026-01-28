package com.abfinance.api.examples.websocket.api;

import com.abfinance.api.client.config.ABFinanceApiConfig;
import com.abfinance.api.client.service.ABFinanceApiClientFactory;

import java.util.Map;

import static com.abfinance.api.client.config.ABFinanceApiConfig.V5_TRADE;

public class WsStreamAPITrade {
    public static void main(String[] args) {
        var client = ABFinanceApiClientFactory.newInstance("YOUR_API_KEY", "YOUR_API_SECRET", ABFinanceApiConfig.STREAM_TESTNET_DOMAIN).newWebsocketClient(20);
        var webSocket = client.getTradeChannelStream(Map.of("reqId", "test-001", "category", "spot", "symbol", "XRPUSDT",
                "side", "Buy", "orderType", "Market", "qty", "10", "orderLinkId", "t0003"), V5_TRADE);

        client.sendSubscribeMessage(webSocket, Map.of("reqId", "test-002", "category", "spot", "symbol", "XRPUSDT",
                "side", "Buy", "orderType", "Market", "qty", "10","orderLinkId", "t0004"));

        // Close websocket
        //client.onClose(webSocket, 1000, "close normal");
    }
}
