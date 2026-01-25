package com.abfinance.api.client.restApi;

import com.abfinance.api.client.domain.earn.request.EarnDataRequest;

/**
 * ABFinance Earn REST API client interface.
 */
public interface ABFinanceApiEarnRestClient {

    /**
     * Get Earn Product List
     * GET /v5/earn/product
     */
    Object getEarnProduct(EarnDataRequest request);

    /**
     * Get Earn Position
     * GET /v5/earn/position
     */
    Object getEarnPosition(EarnDataRequest request);

    /**
     * Place Earn Order
     * POST /v5/earn/place-order
     */
    Object placeEarnOrder(EarnDataRequest request);

    /**
     * Get Earn Order
     * GET /v5/earn/order
     */
    Object getEarnOrder(EarnDataRequest request);

    /**
     * Get Earn Yield
     * GET /v5/earn/yield
     */
    Object getEarnYield(EarnDataRequest request);

    /**
     * Get Earn Hourly Yield
     * GET /v5/earn/hourly-yield
     */
    Object getEarnHourlyYield(EarnDataRequest request);
}
