package com.abfinance.api.client.impl;

import com.abfinance.api.client.domain.apilimit.request.ApiLimitDataRequest;
import com.abfinance.api.client.domain.apilimit.request.SetApiLimitRequest;
import com.abfinance.api.client.restApi.ABFinanceApiAsyncLimitRestClient;
import com.abfinance.api.client.restApi.ABFinanceApiCallback;
import com.abfinance.api.client.restApi.ABFinanceApiService;

import static com.abfinance.api.client.service.ABFinanceApiServiceGenerator.createService;

/**
 * ABFinance API Limit REST API async client implementation.
 */
public class ABFinanceApiAsyncLimitRestClientImpl implements ABFinanceApiAsyncLimitRestClient {
    private final ABFinanceApiService apiService;

    public ABFinanceApiAsyncLimitRestClientImpl(String apiKey, String secret, String baseUrl, boolean debugMode, long recvWindow, String logOption) {
        apiService = createService(ABFinanceApiService.class, apiKey, secret, baseUrl, debugMode, recvWindow, logOption, "");
    }

    @Override
    public void getApiLimit(ApiLimitDataRequest request, ABFinanceApiCallback<Object> callback) {
        apiService.getApiLimit(request.getQuotaId()).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getAllApiLimit(ABFinanceApiCallback<Object> callback) {
        apiService.getAllApiLimit().enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getApiLimitCap(ABFinanceApiCallback<Object> callback) {
        apiService.getApiLimitCap().enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void setApiLimit(ApiLimitDataRequest request, ABFinanceApiCallback<Object> callback) {
        SetApiLimitRequest setApiLimitRequest = SetApiLimitRequest.builder()
                .quotaId(request.getQuotaId())
                .dailyCap(request.getDailyCap())
                .build();
        apiService.setApiLimit(setApiLimitRequest).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }
}
