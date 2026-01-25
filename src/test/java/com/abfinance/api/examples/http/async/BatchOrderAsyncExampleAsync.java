package com.abfinance.api.examples.http.async;

import com.abfinance.api.client.config.ABFinanceApiConfig;
import com.abfinance.api.client.domain.trade.request.BatchOrderRequest;
import com.abfinance.api.client.domain.trade.request.PlaceBatchOrderRequest;
import com.abfinance.api.client.domain.trade.request.TradeOrderRequest;
import com.abfinance.api.client.restApi.ABFinanceApiAsyncTradeRestClient;
import com.abfinance.api.client.domain.CategoryType;
import com.abfinance.api.client.domain.TradeOrderType;
import com.abfinance.api.client.domain.trade.*;
import com.abfinance.api.client.service.ABFinanceApiClientFactory;
import java.util.Arrays;

public class BatchOrderAsyncExampleAsync {
    public static void main(String[] args) {
        var client = ABFinanceApiClientFactory.newInstance("YOUR_API_KEY", "YOUR_API_SECRET", ABFinanceApiConfig.TESTNET_DOMAIN).newAsyncTradeRestClient();

        // Create a batch order
        var orderRequests = Arrays.asList(TradeOrderRequest.builder().category(CategoryType.OPTION).symbol("BTC-10FEB23-24000-C").side(Side.BUY).orderType(TradeOrderType.LIMIT).qty("0.1")
                        .price("5").orderIv("0.1").timeInForce(TimeInForce.GOOD_TILL_CANCEL).orderLinkId("9b381bb1-401").mmp(false).reduceOnly(false).build(),
                TradeOrderRequest.builder().category(CategoryType.OPTION).symbol("BTC-10FEB23-24000-C").side(Side.BUY).orderType(TradeOrderType.LIMIT).qty("0.1")
                        .price("5").orderIv("0.1").timeInForce(TimeInForce.GOOD_TILL_CANCEL).orderLinkId("82ee86dd-001").mmp(false).reduceOnly(false).build());
        var createBatchOrders = BatchOrderRequest.builder().category(CategoryType.OPTION).request(orderRequests).build();
        client.createBatchOrder(createBatchOrders, System.out::println);

        // Amend a batch order
        var amendOrderRequests = Arrays.asList(TradeOrderRequest.builder().symbol("BTC-10FEB23-24000-C").qty("0.1").price("5").orderLinkId("9b381bb1-401").build(),
                TradeOrderRequest.builder().symbol("BTC-10FEB23-24000-C").qty("0.1").price("5").orderLinkId("82ee86dd-001").build());
        var amendBatchOrders = BatchOrderRequest.builder().category(CategoryType.OPTION).request(amendOrderRequests).build();
        client.createBatchOrder(amendBatchOrders, System.out::println);

        // Cancel a batch order
        var cancelOrderRequests = Arrays.asList(TradeOrderRequest.builder().symbol("BTC-10FEB23-24000-C").orderLinkId("9b381bb1-401").build(),
                TradeOrderRequest.builder().symbol("BTC-10FEB23-24000-C").orderLinkId("82ee86dd-001").build());
        var cancelBatchOrders = BatchOrderRequest.builder().category(CategoryType.OPTION).request(cancelOrderRequests).build();
        client.createBatchOrder(cancelBatchOrders, System.out::println);
    }
}
