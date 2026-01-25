package com.abfinance.api.client.exception;

import lombok.Getter;

/**
 * An exception which can occur while invoking methods of the ABFinance API.
 */
@Getter
public class ABFinanceApiException extends RuntimeException {

    private static final long serialVersionUID = 3788669840036201041L;
    /**
     * Error response object returned by ABFinance API.
     * -- GETTER --
     */
    private ABFinanceApiError error;

    /**
     * Instantiates a new ABFinance api exception.
     *
     * @param error an error response object
     */
    public ABFinanceApiException(ABFinanceApiError error) {
        this.error = error;
    }

    /**
     * Instantiates a new ABFinance api exception.
     */
    public ABFinanceApiException() {
        super();
    }

    /**
     * Instantiates a new ABFinance api exception.
     *
     * @param message the message
     */
    public ABFinanceApiException(String message) {
        super(message);
    }

    /**
     * Instantiates a new ABFinance api exception.
     *
     * @param cause the cause
     */
    public ABFinanceApiException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new ABFinance api exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public ABFinanceApiException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        if (error != null) {
            return error.getMsg();
        }
        return super.getMessage();
    }
}
