package com.abfinance.api.client.impl;

import com.abfinance.api.client.restApi.ABFinanceApiEarnRestClient;
import com.abfinance.api.client.restApi.ABFinanceApiService;
import com.abfinance.api.client.domain.earn.request.EarnDataRequest;

import java.util.HashMap;
import java.util.Map;

import static com.abfinance.api.client.service.ABFinanceApiServiceGenerator.createService;
import static com.abfinance.api.client.service.ABFinanceApiServiceGenerator.executeSync;

/**
 * ABFinance Earn REST API client implementation.
 */
public class ABFinanceApiEarnRestClientImpl implements ABFinanceApiEarnRestClient {
    private final ABFinanceApiService apiService;

    public ABFinanceApiEarnRestClientImpl(ABFinanceApiService apiService) {
        this.apiService = apiService;
    }

    public ABFinanceApiEarnRestClientImpl(String apiKey, String secret, String baseUrl, boolean debugMode, long recvWindow, String logOption) {
        apiService = createService(ABFinanceApiService.class, apiKey, secret, baseUrl, debugMode, recvWindow, logOption, "");
    }

    @Override
    public Object getEarnProduct(EarnDataRequest request) {
        return executeSync(apiService.getEarnProduct(
                request.getCategory(),
                request.getCoin(),
                request.getProductId(),
                request.getLimit(),
                request.getCursor()
        ));
    }

    @Override
    public Object getEarnPosition(EarnDataRequest request) {
        return executeSync(apiService.getEarnPosition(
                request.getCategory(),
                request.getCoin(),
                request.getProductId(),
                request.getLimit(),
                request.getCursor()
        ));
    }

    @Override
    public Object placeEarnOrder(EarnDataRequest request) {
        Map<String, Object> params = new HashMap<>();
        if (request.getProductId() != null) params.put("productId", request.getProductId());
        if (request.getQuantity() != null) params.put("quantity", request.getQuantity());
        if (request.getAccountType() != null) params.put("accountType", request.getAccountType());
        if (request.getSerialNo() != null) params.put("serialNo", request.getSerialNo());
        return executeSync(apiService.placeEarnOrder(params));
    }

    @Override
    public Object getEarnOrder(EarnDataRequest request) {
        return executeSync(apiService.getEarnOrder(
                request.getOrderId(),
                request.getOrderType(),
                request.getCategory(),
                request.getCoin(),
                request.getProductId(),
                request.getStartTime(),
                request.getEndTime(),
                request.getLimit(),
                request.getCursor()
        ));
    }
}
