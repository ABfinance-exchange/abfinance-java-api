package com.abfinance.api.client.impl;

import com.abfinance.api.client.domain.apilimit.request.ApiLimitDataRequest;
import com.abfinance.api.client.domain.apilimit.request.SetApiLimitRequest;
import com.abfinance.api.client.restApi.ABFinanceApiLimitRestClient;
import com.abfinance.api.client.restApi.ABFinanceApiService;

import static com.abfinance.api.client.service.ABFinanceApiServiceGenerator.createService;
import static com.abfinance.api.client.service.ABFinanceApiServiceGenerator.executeSync;

/**
 * ABFinance API Limit REST API client implementation.
 */
public class ABFinanceApiLimitRestClientImpl implements ABFinanceApiLimitRestClient {
    private final ABFinanceApiService apiService;

    public ABFinanceApiLimitRestClientImpl(String apiKey, String secret, String baseUrl, boolean debugMode, long recvWindow, String logOption) {
        apiService = createService(ABFinanceApiService.class, apiKey, secret, baseUrl, debugMode, recvWindow, logOption, "");
    }

    @Override
    public Object getApiLimit(ApiLimitDataRequest request) {
        return executeSync(apiService.getApiLimit(request.getQuotaId()));
    }

    @Override
    public Object getAllApiLimit() {
        return executeSync(apiService.getAllApiLimit());
    }

    @Override
    public Object getApiLimitCap() {
        return executeSync(apiService.getApiLimitCap());
    }

    @Override
    public Object setApiLimit(ApiLimitDataRequest request) {
        SetApiLimitRequest setApiLimitRequest = SetApiLimitRequest.builder()
                .quotaId(request.getQuotaId())
                .dailyCap(request.getDailyCap())
                .build();
        return executeSync(apiService.setApiLimit(setApiLimitRequest));
    }
}
