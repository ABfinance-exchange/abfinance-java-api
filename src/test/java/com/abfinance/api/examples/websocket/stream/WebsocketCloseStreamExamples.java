package com.abfinance.api.examples.websocket.stream;

import com.abfinance.api.client.config.ABFinanceApiConfig;
import com.abfinance.api.client.service.ABFinanceApiClientFactory;
import okhttp3.WebSocket;

import java.util.List;

public class WebsocketCloseStreamExamples {
    public static void main(String[] args) {
        var client = ABFinanceApiClientFactory.newInstance(ABFinanceApiConfig.STREAM_MAINNET_DOMAIN, true).newWebsocketClient(5, System.out::println);

        // Ticker
        WebSocket webSocket = client.getPublicChannelStream(List.of("tickers.BTCUSDT"), ABFinanceApiConfig.V5_PUBLIC_LINEAR);

        // close websocket
        client.onClose(webSocket, 1000, "close normal");

/*        client.setMessageHandler(message -> {
            // Process message data here
            System.out.println("Websocket Message Data: " + message);
        });*/

        // Subscribe Orderbook more than one args
        //client.getPublicChannelStream(List.of("orderbook.25.BTC-23FEB24-51000-P"), ABFinanceApiConfig.V5_PUBLIC_OPTION);
        // Orderbook
        // client.getPublicChannelStream(List.of("orderbook.50.BTCUSDT"), ABFinanceApiConfig.V5_PUBLIC_LINEAR);

        // Trade
        // client.getPublicChannelStream(List.of("publicTrade.BTCUSDT"), ABFinanceApiConfig.V5_PUBLIC_LINEAR);


        // Kline
        // client.getPublicChannelStream(List.of("kline.D.BTCUSDT"), ABFinanceApiConfig.V5_PUBLIC_LINEAR);

        // Liquidation
        // client.getPublicChannelStream(List.of("liquidation.BTCUSDT"), ABFinanceApiConfig.V5_PUBLIC_LINEAR);

        // LT Kline
        // client.getPublicChannelStream(List.of("kline_lt.1.EOS3LUSDT"), ABFinanceApiConfig.V5_PUBLIC_SPOT);

        // LT Ticker
        // client.getPublicChannelStream(List.of("tickers_lt.1.EOS3LUSDT"), ABFinanceApiConfig.V5_PUBLIC_SPOT);

        // LT Nav
        // client.getPublicChannelStream(List.of("lt.EOS3LUSDT"), ABFinanceApiConfig.V5_PUBLIC_SPOT);
    }
}
