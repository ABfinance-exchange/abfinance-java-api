package com.abfinance.api.client.impl;

import com.abfinance.api.client.domain.trade.request.BatchOrderRequest;
import com.abfinance.api.client.restApi.ABFinanceApiAsyncTradeRestClient;
import com.abfinance.api.client.restApi.ABFinanceApiCallback;
import com.abfinance.api.client.restApi.ABFinanceApiService;
import com.abfinance.api.client.domain.trade.request.TradeOrderRequest;
import com.abfinance.api.client.service.ABFinanceJsonConverter;

import java.io.IOException;
import java.util.Map;

import static com.abfinance.api.client.service.ABFinanceApiServiceGenerator.createService;

public class ABFinanceApiTradeAsyncRestClientImpl implements ABFinanceApiAsyncTradeRestClient {
    private final ABFinanceApiService apiService;
    private final ABFinanceJsonConverter converter = new ABFinanceJsonConverter();

    public ABFinanceApiTradeAsyncRestClientImpl(String apiKey, String secret, String baseUrl, boolean debugMode, long recvWindow, String logOption, String referer) {
        apiService = createService(ABFinanceApiService.class, apiKey, secret, baseUrl, debugMode, recvWindow, logOption, referer);
    }

    @Override
    public void getOrderHistory(TradeOrderRequest orderHistoryRequest, ABFinanceApiCallback<Object> callback) {
        apiService.getOrderHistory(
                        orderHistoryRequest.getCategory().getCategoryTypeId(),
                        orderHistoryRequest.getSymbol(),
                        orderHistoryRequest.getBaseCoin(),
                        orderHistoryRequest.getSettleCoin(),
                        orderHistoryRequest.getOrderId(),
                        orderHistoryRequest.getOrderLinkId(),
                        orderHistoryRequest.getOrderFilter() == null ? null : orderHistoryRequest.getOrderFilter().getOrderFilterType(),
                        orderHistoryRequest.getOrderStatus(),
                        orderHistoryRequest.getStartTime(),
                        orderHistoryRequest.getEndTime(),
                        orderHistoryRequest.getLimit(),
                        orderHistoryRequest.getCursor())
                .enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void setDisconnectCancelAllTime(TradeOrderRequest orderRequest, ABFinanceApiCallback<Object> callback) {
        var setDcpRequest = converter.convertMapToDcpRequest(orderRequest);
        apiService.setDisconnectCancelAllTime(setDcpRequest).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getBorrowQuota(TradeOrderRequest borrowQuotaRequest, ABFinanceApiCallback<Object> callback) {
        apiService.getBorrowQuota(
                        borrowQuotaRequest.getCategory().getCategoryTypeId(),
                        borrowQuotaRequest.getSymbol(),
                        borrowQuotaRequest.getSide() == null ? null : borrowQuotaRequest.getSide().getTransactionSide())
                .enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getOpenOrders(TradeOrderRequest order, ABFinanceApiCallback<Object> callback) {
        apiService.getOpenOrders(
                        order.getCategory().getCategoryTypeId(),
                        order.getSymbol(),
                        order.getBaseCoin(),
                        order.getSettleCoin(),
                        order.getOrderId(),
                        order.getOrderLinkId(),
                        order.getOpenOnly(),
                        order.getOrderFilter() == null ? null : order.getOrderFilter().getOrderFilterType(),
                        order.getLimit(),
                        order.getCursor())
                .enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getTradeHistory(TradeOrderRequest order, ABFinanceApiCallback<Object> callback) {
        apiService.getTradeHistory(
                order.getCategory().getCategoryTypeId(),
                order.getSymbol(),
                order.getOrderId(),
                order.getOrderLinkId(),
                order.getBaseCoin(),
                order.getStartTime(),
                order.getEndTime(),
                order.getExecType() == null ? null : order.getExecType().getExecTypeId(),
                order.getLimit(),
                order.getCursor()
        ).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void createOrder(TradeOrderRequest order, ABFinanceApiCallback<Object> callback) {
        var placeOrderRequest = converter.convertTradeToPlaceOrderRequest(order);
        apiService.createOrder(placeOrderRequest).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void createOrder(Map<String, Object> order, ABFinanceApiCallback<Object> callback) {
        var singleOrderRequest = converter.convertMapToSingleOrderRequest(order);
        var placeOrderRequest = converter.convertTradeToPlaceOrderRequest(singleOrderRequest);
        apiService.createOrder(placeOrderRequest).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void createOrder(String order, ABFinanceApiCallback<Object> callback) throws IOException {
        var singleOrderRequest = converter.convertJsonToSingleOrderRequest(order);
        var placeOrderRequest = converter.convertTradeToPlaceOrderRequest(singleOrderRequest);
        apiService.createOrder(placeOrderRequest).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void createBatchOrder(BatchOrderRequest batchOrderRequest, ABFinanceApiCallback<Object> callback) {
        var placeBatchOrderRequest = converter.convertToPlaceBatchOrderRequest(batchOrderRequest);
        apiService.createBatchOrder(placeBatchOrderRequest).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void createBathOrder(Map<String, Object> payload, ABFinanceApiCallback<Object> callback) {
        var batchOrderRequest = converter.convertMapToBatchOrderRequest(payload);
        var placeBatchOrderRequest = converter.convertToPlaceBatchOrderRequest(batchOrderRequest);
        apiService.createBatchOrder(placeBatchOrderRequest).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void createBathOrder(String json, ABFinanceApiCallback<Object> callback) throws IOException {
        var batchOrderRequest = converter.jsonToBatchOrderRequest(json);
        var placeBatchOrderRequest = converter.convertToPlaceBatchOrderRequest(batchOrderRequest);
        apiService.createBatchOrder(placeBatchOrderRequest).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void amendBatchOrder(BatchOrderRequest batchOrderRequest, ABFinanceApiCallback<Object> callback) {
        var amendBatchOrderRequest = converter.convertToAmendBatchOrderRequest(batchOrderRequest);
        apiService.amendBatchOrder(amendBatchOrderRequest).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void amendBatchOrder(Map<String, Object> payload, ABFinanceApiCallback<Object> callback) {
        var batchOrderRequest = converter.convertMapToBatchOrderRequest(payload);
        var amendBatchOrderRequest = converter.convertToAmendBatchOrderRequest(batchOrderRequest);
        apiService.amendBatchOrder(amendBatchOrderRequest).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void amendBatchOrder(String json, ABFinanceApiCallback<Object> callback) throws IOException {
        var batchOrderRequest = converter.jsonToBatchOrderRequest(json);
        var amendBatchOrderRequest = converter.convertToAmendBatchOrderRequest(batchOrderRequest);
        apiService.amendBatchOrder(amendBatchOrderRequest).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void cancelBatchOrder(BatchOrderRequest batchOrderRequest, ABFinanceApiCallback<Object> callback) {
        var cancelBatchOrderRequest = converter.convertToCancelBatchOrderRequest(batchOrderRequest);
        apiService.cancelBatchOrder(cancelBatchOrderRequest).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void cancelBatchOrder(Map<String, Object> payload, ABFinanceApiCallback<Object> callback) {
        var batchOrderRequest = converter.convertMapToBatchOrderRequest(payload);
        var cancelBatchOrderRequest = converter.convertToCancelBatchOrderRequest(batchOrderRequest);
        apiService.cancelBatchOrder(cancelBatchOrderRequest).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void cancelBatchOrder(String json, ABFinanceApiCallback<Object> callback) throws IOException {
        var batchOrderRequest = converter.jsonToBatchOrderRequest(json);
        var cancelBatchOrderRequest = converter.convertToCancelBatchOrderRequest(batchOrderRequest);
        apiService.cancelBatchOrder(cancelBatchOrderRequest).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void cancelOrder(TradeOrderRequest order, ABFinanceApiCallback<Object> callback) {
        var cancelOrderRequest = converter.convertTradeToCancelOrderRequest(order);
        apiService.cancelOrder(cancelOrderRequest).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void cancelAllOrder(TradeOrderRequest order, ABFinanceApiCallback<Object> callback) {
        var cancelAllOrderRequest = converter.convertTradeToCancelAllOrdersRequest(order);
        apiService.cancelAllOrder(cancelAllOrderRequest).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void amendOrder(TradeOrderRequest order, ABFinanceApiCallback<Object> callback) {
        var amendOrderRequest = converter.convertTradeToAmendOrderRequest(order);
        apiService.amendOrder(amendOrderRequest).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }
}
