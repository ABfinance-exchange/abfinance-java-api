package com.abfinance.api.client.restApi;

import com.abfinance.api.client.domain.earn.request.EarnDataRequest;

/**
 * ABFinance Earn REST API async client interface.
 */
public interface ABFinanceApiAsyncEarnRestClient {

    /**
     * Get Earn Product List
     * GET /v5/earn/product
     */
    void getEarnProduct(EarnDataRequest request, ABFinanceApiCallback<Object> callback);

    /**
     * Get Earn Position
     * GET /v5/earn/position
     */
    void getEarnPosition(EarnDataRequest request, ABFinanceApiCallback<Object> callback);

    /**
     * Place Earn Order
     * POST /v5/earn/place-order
     */
    void placeEarnOrder(EarnDataRequest request, ABFinanceApiCallback<Object> callback);

    /**
     * Get Earn Order
     * GET /v5/earn/order
     */
    void getEarnOrder(EarnDataRequest request, ABFinanceApiCallback<Object> callback);
}
