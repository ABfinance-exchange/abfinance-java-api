package com.abfinance.api.client.impl;

import com.abfinance.api.client.restApi.ABFinanceApiAccountRestClient;
import com.abfinance.api.client.restApi.ABFinanceApiService;
import com.abfinance.api.client.domain.account.request.AccountDataRequest;

import static com.abfinance.api.client.service.ABFinanceApiServiceGenerator.createService;
import static com.abfinance.api.client.service.ABFinanceApiServiceGenerator.executeSync;

public class ABFinanceApiAccountRestClientImpl implements ABFinanceApiAccountRestClient {
    private final ABFinanceApiService apiService;

    public ABFinanceApiAccountRestClientImpl(String apiKey, String secret, String baseUrl, boolean debugMode, long recvWindow, String logOption) {
        apiService = createService(ABFinanceApiService.class, apiKey, secret, baseUrl, debugMode, recvWindow, logOption, "");
    }

    // Account endpoints
    @Override
    public Object getWalletBalance(AccountDataRequest walletBalanceRequest) {
        return executeSync(apiService.getWalletBalance(
                walletBalanceRequest.getAccountType() == null ? null : walletBalanceRequest.getAccountType().getAccountTypeValue(),
                walletBalanceRequest.getCoins()
        ));
    }

    @Override
    public Object getAccountFreeRate(AccountDataRequest getFeeRateRequest) {
        return executeSync(apiService.getAccountFreeRate(
                getFeeRateRequest.getCategory().getCategoryTypeId(),
                getFeeRateRequest.getSymbol(),
                getFeeRateRequest.getBaseCoin()
        ));
    }

    @Override
    public Object getAccountInfo() {
        return executeSync(apiService.getAccountInfo());
    }

    @Override
    public Object getTransactionLog(AccountDataRequest getTransactionLogRequest) {
        return executeSync(apiService.getUtaTransactionLog(
                getTransactionLogRequest.getAccountType() == null ? null : getTransactionLogRequest.getAccountType().getAccountTypeValue(),
                getTransactionLogRequest.getCategory() == null ? null : getTransactionLogRequest.getCategory().getCategoryTypeId(),
                getTransactionLogRequest.getCurrency(),
                getTransactionLogRequest.getBaseCoin(),
                null,
                getTransactionLogRequest.getStartTime(),
                getTransactionLogRequest.getEndTime(),
                getTransactionLogRequest.getLimit(),
                getTransactionLogRequest.getCursor()
        ));
    }

    @Override
    public Object getAccountSMPGroup() {
        return executeSync(apiService.getAccountSMPGroupId());
    }

    @Override
    public Object getUserSettingConfig() {
        return executeSync(apiService.getUserSettingConfig());
    }
}
