package com.abfinance.api.client.impl;

import com.abfinance.api.client.restApi.ABFinanceApiAsyncEarnRestClient;
import com.abfinance.api.client.restApi.ABFinanceApiCallback;
import com.abfinance.api.client.restApi.ABFinanceApiService;
import com.abfinance.api.client.domain.earn.request.EarnDataRequest;

import java.util.HashMap;
import java.util.Map;

import static com.abfinance.api.client.service.ABFinanceApiServiceGenerator.createService;

/**
 * ABFinance Earn REST API async client implementation.
 */
public class ABFinanceApiAsyncEarnRestClientImpl implements ABFinanceApiAsyncEarnRestClient {
    private final ABFinanceApiService apiService;

    public ABFinanceApiAsyncEarnRestClientImpl(ABFinanceApiService apiService) {
        this.apiService = apiService;
    }

    public ABFinanceApiAsyncEarnRestClientImpl(String apiKey, String secret, String baseUrl, boolean debugMode, long recvWindow, String logOption) {
        apiService = createService(ABFinanceApiService.class, apiKey, secret, baseUrl, debugMode, recvWindow, logOption, "");
    }

    @Override
    public void getEarnProduct(EarnDataRequest request, ABFinanceApiCallback<Object> callback) {
        apiService.getEarnProduct(
                request.getCategory(),
                request.getCoin(),
                request.getProductId(),
                request.getLimit(),
                request.getCursor()
        ).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getEarnPosition(EarnDataRequest request, ABFinanceApiCallback<Object> callback) {
        apiService.getEarnPosition(
                request.getCategory(),
                request.getCoin(),
                request.getProductId(),
                request.getLimit(),
                request.getCursor()
        ).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void placeEarnOrder(EarnDataRequest request, ABFinanceApiCallback<Object> callback) {
        Map<String, Object> params = new HashMap<>();
        if (request.getProductId() != null) params.put("productId", request.getProductId());
        if (request.getQuantity() != null) params.put("quantity", request.getQuantity());
        if (request.getAccountType() != null) params.put("accountType", request.getAccountType());
        if (request.getSerialNo() != null) params.put("serialNo", request.getSerialNo());
        apiService.placeEarnOrder(params).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getEarnOrder(EarnDataRequest request, ABFinanceApiCallback<Object> callback) {
        apiService.getEarnOrder(
                request.getOrderId(),
                request.getOrderType(),
                request.getCategory(),
                request.getCoin(),
                request.getProductId(),
                request.getStartTime(),
                request.getEndTime(),
                request.getLimit(),
                request.getCursor()
        ).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getEarnYield(EarnDataRequest request, ABFinanceApiCallback<Object> callback) {
        apiService.getEarnYield(
                request.getCategory(),
                request.getCoin(),
                request.getProductId(),
                request.getStartTime(),
                request.getEndTime(),
                request.getLimit(),
                request.getCursor()
        ).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getEarnHourlyYield(EarnDataRequest request, ABFinanceApiCallback<Object> callback) {
        apiService.getEarnHourlyYield(
                request.getCategory(),
                request.getCoin(),
                request.getProductId(),
                request.getStartTime(),
                request.getEndTime(),
                request.getLimit(),
                request.getCursor()
        ).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }
}
