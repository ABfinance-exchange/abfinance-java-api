package com.abfinance.api.client.impl;

import com.abfinance.api.client.restApi.ABFinanceApiAsyncAccountRestClient;
import com.abfinance.api.client.restApi.ABFinanceApiCallback;
import com.abfinance.api.client.restApi.ABFinanceApiService;
import com.abfinance.api.client.domain.account.request.AccountDataRequest;

import static com.abfinance.api.client.service.ABFinanceApiServiceGenerator.createService;

public class ABFinanceApiAsyncAccountRestClientImpl implements ABFinanceApiAsyncAccountRestClient {
    private final ABFinanceApiService apiService;

    public ABFinanceApiAsyncAccountRestClientImpl(String apiKey, String secret, String baseUrl, boolean debugMode, long recvWindow, String logOption) {
        apiService = createService(ABFinanceApiService.class, apiKey, secret, baseUrl, debugMode, recvWindow, logOption, "");
    }

    // Account Endpoints
    @Override
    public void getWalletBalance(AccountDataRequest walletBalanceRequest, ABFinanceApiCallback<Object> callback) {
        apiService.getWalletBalance(
                walletBalanceRequest.getAccountType().getAccountTypeValue(),
                walletBalanceRequest.getCoins()
        ).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getAccountFreeRate(AccountDataRequest getFeeRateRequest, ABFinanceApiCallback<Object> callback) {
        apiService.getAccountFreeRate(
                getFeeRateRequest.getCategory().getCategoryTypeId(),
                getFeeRateRequest.getSymbol(),
                getFeeRateRequest.getBaseCoin()
        ).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getAccountInfo(ABFinanceApiCallback<Object> callback) {
        apiService.getAccountInfo().enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getTransactionLog(AccountDataRequest getTransactionLogRequest, ABFinanceApiCallback<Object> callback) {
        apiService.getUtaTransactionLog(
                getTransactionLogRequest.getAccountType() == null ? null : getTransactionLogRequest.getAccountType().getAccountTypeValue(),
                getTransactionLogRequest.getCategory() == null ? null : getTransactionLogRequest.getCategory().getCategoryTypeId(),
                getTransactionLogRequest.getCurrency(),
                getTransactionLogRequest.getBaseCoin(),
                null,
                getTransactionLogRequest.getStartTime(),
                getTransactionLogRequest.getEndTime(),
                getTransactionLogRequest.getLimit(),
                getTransactionLogRequest.getCursor()
        ).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getAccountSMPGroup(ABFinanceApiCallback<Object> callback) {
        apiService.getAccountSMPGroupId().enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getUserSettingConfig(ABFinanceApiCallback<Object> callback) {
        apiService.getUserSettingConfig().enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }
}
