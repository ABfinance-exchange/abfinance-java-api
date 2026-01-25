package com.abfinance.api.client.restApi;

/**
 * ABFinanceApiCallback is a functional interface used together with the ABFinanceApiAsyncClient to provide a non-blocking REST client.
 *
 * @param <T> the return type from the callback
 */
public interface ABFinanceApiCallback<T> {

    /**
     * Called whenever a response comes back from the ABFinance API.
     *
     * @param response the expected response object
     */
    void onResponse(T response);

    /**
     * Called whenever an error occurs.
     *
     * @param cause the cause of the failure
     */
    default void onFailure(Throwable cause) {}
}
