package com.abfinance.api.client.restApi;

import com.abfinance.api.client.domain.apilimit.request.ApiLimitDataRequest;

/**
 * ABFinance API Limit REST API client interface.
 */
public interface ABFinanceApiLimitRestClient {

    /**
     * Get API Limit
     * GET /v5/apilimit/query
     */
    Object getApiLimit(ApiLimitDataRequest request);

    /**
     * Get All API Limit
     * GET /v5/apilimit/query-all
     */
    Object getAllApiLimit();

    /**
     * Get API Limit Cap
     * GET /v5/apilimit/query-cap
     */
    Object getApiLimitCap();

    /**
     * Set API Limit
     * POST /v5/apilimit/set
     */
    Object setApiLimit(ApiLimitDataRequest request);
}
