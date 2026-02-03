package com.abfinance.api.client.impl;

import com.abfinance.api.client.restApi.ABFinanceApiCallback;
import com.abfinance.api.client.exception.ABFinanceApiError;
import com.abfinance.api.client.exception.ABFinanceApiException;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

import static com.abfinance.api.client.service.ABFinanceApiServiceGenerator.getABFinanceApiError;

public class ABFinanceApiCallbackAdapter<T> implements Callback<T> {

    private final ABFinanceApiCallback<T> callback;

    public ABFinanceApiCallbackAdapter(ABFinanceApiCallback<T> callback) {
        this.callback = callback;
    }

    public void onResponse(@NotNull Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            callback.onResponse(response.body());
        } else {
            if (response.code() == 504) {
                // HTTP 504 return code is used when the API successfully sent the message but not get a response within the timeout period.
                // It is important to NOT treat this as a failure; the execution status is UNKNOWN and could have been a success.
                // Notify the caller about the timeout so they can handle it appropriately.
                onFailure(call, new ABFinanceApiException("Request timeout (504): execution status is UNKNOWN and could have been a success."));
                return;
            }
            try {
                ABFinanceApiError apiError = getABFinanceApiError(response);
                onFailure(call, new ABFinanceApiException(apiError));
            } catch (IOException e) {
                onFailure(call, new ABFinanceApiException(e));
            }
        }
    }

    @Override
    public void onFailure(@NotNull Call<T> call, @NotNull Throwable throwable) {
        if (throwable instanceof ABFinanceApiException) {
            callback.onFailure(throwable);
        } else {
            callback.onFailure(new ABFinanceApiException(throwable));
        }
    }
}
