package com.abfinance.api.websocket.stream;

import com.abfinance.api.client.config.ABFinanceApiConfig;
import com.abfinance.api.client.service.ABFinanceApiClientFactory;
import org.junit.Test;

import java.util.List;

public class PublicChannelTest {
    @Test
    public void Test_GetOrderBookStream1()
    {
        // create websocket with message handler and in debug mode
        ABFinanceApiClientFactory factory = ABFinanceApiClientFactory.newInstance();
        var client = factory.newWebsocketClient((message) -> System.out.println("Handle message :" + message));
        client.getPublicChannelStream(List.of("orderbook.50.BTCUSDT"), ABFinanceApiConfig.V5_PUBLIC_LINEAR);
    }

    @Test
    public void Test_GetOrderBookStream2()
    {
        // create websocket with message handler and  not in debug mode
        ABFinanceApiClientFactory factory = ABFinanceApiClientFactory.newInstance();
        var client = factory.newWebsocketClient((message) -> System.out.println("Handle message :" + message));
        client.getPublicChannelStream(List.of("orderbook.50.BTCUSDT"), ABFinanceApiConfig.V5_PUBLIC_LINEAR);
    }

    @Test
    public void Test_GetOrderBookStream3()
    {
        // create websocket without message handler and in debug mode
        ABFinanceApiClientFactory factory = ABFinanceApiClientFactory.newInstance();
        var client = factory.newWebsocketClient();
        client.getPublicChannelStream(List.of("orderbook.50.BTCUSDT"), ABFinanceApiConfig.V5_PUBLIC_LINEAR);
    }

    @Test
    public void Test_GetOrderBookStream4()
    {
        // create websocket without message handler and  not in debug mode
        ABFinanceApiClientFactory factory = ABFinanceApiClientFactory.newInstance();
        var client = factory.newWebsocketClient();
        client.getPublicChannelStream(List.of("orderbook.50.BTCUSDT"), ABFinanceApiConfig.V5_PUBLIC_LINEAR);
    }

    @Test
    public void Test_GetOrderBookStream5()
    {
        // Subscribe more than one args
        ABFinanceApiClientFactory factory = ABFinanceApiClientFactory.newInstance(false);
        var client = factory.newWebsocketClient();
        client.getPublicChannelStream(List.of("orderbook.50.BTCUSDT","orderbook.1.ETHUSDT"), ABFinanceApiConfig.V5_PUBLIC_LINEAR);
    }
}
