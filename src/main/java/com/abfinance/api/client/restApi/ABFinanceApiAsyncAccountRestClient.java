package com.abfinance.api.client.restApi;

import com.abfinance.api.client.domain.account.request.AccountDataRequest;

public interface ABFinanceApiAsyncAccountRestClient {
    // Account endpoints
    void getWalletBalance(AccountDataRequest walletBalanceRequest, ABFinanceApiCallback<Object> callback);
    void getAccountFreeRate(AccountDataRequest getFeeRateRequest, ABFinanceApiCallback<Object> callback);
    void getAccountInfo(ABFinanceApiCallback<Object> callback);
    void getTransactionLog(AccountDataRequest getTransactionLogRequest, ABFinanceApiCallback<Object> callback);
    void getAccountSMPGroup(ABFinanceApiCallback<Object> callback);
    void getUserSettingConfig(ABFinanceApiCallback<Object> callback);
}
