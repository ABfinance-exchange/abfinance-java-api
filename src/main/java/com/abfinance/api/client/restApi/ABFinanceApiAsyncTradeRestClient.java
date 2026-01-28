package com.abfinance.api.client.restApi;

import com.abfinance.api.client.domain.trade.request.BatchOrderRequest;
import com.abfinance.api.client.domain.trade.request.TradeOrderRequest;

import java.io.IOException;
import java.util.Map;

public interface ABFinanceApiAsyncTradeRestClient {
    // Trade
    void getOrderHistory(TradeOrderRequest orderHistoryRequest, ABFinanceApiCallback<Object> callback);
    void setDisconnectCancelAllTime(TradeOrderRequest tradeOrderRequest, ABFinanceApiCallback<Object> callback);
    void getBorrowQuota(TradeOrderRequest borrowQuotaRequest, ABFinanceApiCallback<Object> callback);
    void getOpenOrders(TradeOrderRequest order, ABFinanceApiCallback<Object> callback);
    void getTradeHistory(TradeOrderRequest order, ABFinanceApiCallback<Object> callback);
    void createOrder(TradeOrderRequest order, ABFinanceApiCallback<Object> callback);
    void createOrder(Map<String, Object> order, ABFinanceApiCallback<Object> callback);
    void createOrder(String order, ABFinanceApiCallback<Object> callback) throws IOException;
    void createBatchOrder(BatchOrderRequest batchOrderRequest, ABFinanceApiCallback<Object> callback);
    void createBathOrder(Map<String, Object> payload, ABFinanceApiCallback<Object> callback);
    void createBathOrder(String json, ABFinanceApiCallback<Object> callback) throws IOException;
    void amendBatchOrder(BatchOrderRequest batchOrderRequest, ABFinanceApiCallback<Object> callback);
    void amendBatchOrder(Map<String, Object> payload, ABFinanceApiCallback<Object> callback);
    void amendBatchOrder(String json, ABFinanceApiCallback<Object> callback) throws IOException;
    void cancelBatchOrder(BatchOrderRequest batchOrderRequest, ABFinanceApiCallback<Object> callback);
    void cancelBatchOrder(Map<String, Object> payload, ABFinanceApiCallback<Object> callback);
    void cancelBatchOrder(String json, ABFinanceApiCallback<Object> callback) throws IOException;
    void cancelOrder(TradeOrderRequest order, ABFinanceApiCallback<Object> callback);
    void cancelAllOrder(TradeOrderRequest order, ABFinanceApiCallback<Object> callback);
    void amendOrder(TradeOrderRequest order, ABFinanceApiCallback<Object> callback);
}
