package com.abfinance.api.client.restApi;

import com.abfinance.api.client.domain.apilimit.request.ApiLimitDataRequest;

/**
 * ABFinance API Limit REST API async client interface.
 */
public interface ABFinanceApiAsyncLimitRestClient {

    /**
     * Get API Limit
     * GET /v5/apilimit/query
     */
    void getApiLimit(ApiLimitDataRequest request, ABFinanceApiCallback<Object> callback);

    /**
     * Get All API Limit
     * GET /v5/apilimit/query-all
     */
    void getAllApiLimit(ABFinanceApiCallback<Object> callback);

    /**
     * Get API Limit Cap
     * GET /v5/apilimit/query-cap
     */
    void getApiLimitCap(ABFinanceApiCallback<Object> callback);

    /**
     * Set API Limit
     * POST /v5/apilimit/set
     */
    void setApiLimit(ApiLimitDataRequest request, ABFinanceApiCallback<Object> callback);
}
